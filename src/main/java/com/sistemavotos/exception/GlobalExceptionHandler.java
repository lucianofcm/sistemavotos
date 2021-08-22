package com.sistemavotos.exception;


import java.util.Objects;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String TRACE = "trace";

    @Value("${reflectoring.trace:false}")
    private boolean printStackTrace;

    @Override
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro desconhecido entre em contato com administrador");
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }
    
    @ExceptionHandler(PautaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> controlarPautaException(PautaException pautaException, WebRequest request) {
        log.error(pautaException.getMessage());
        return contruirReposta(pautaException, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(VotacaoEncerradaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> controlarVotacaoEncerrdaException(VotacaoEncerradaException votacaoEncerradaException, WebRequest request) {
        log.error("Não é possível votar pois a votação já foi encerrda.", votacaoEncerradaException);
        return contruirReposta(votacaoEncerradaException, HttpStatus.BAD_REQUEST, request);
    }
    
    @ExceptionHandler(UsuarioJaVotouException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> controlarUsuarioJaVotouExcetion(UsuarioJaVotouException usuarioJaVotouException, WebRequest request) {
        log.error("O usuário já votou nesta pauta.");
        return contruirReposta(usuarioJaVotouException, HttpStatus.CONFLICT, request);
    }
    
    @ExceptionHandler(UsuarioNaoPodeVotarException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> controlarUsuarioNaoPodeVotarException(UsuarioNaoPodeVotarException usuarioNaoPodeVotarException, WebRequest request) {
        log.error("O usuário não pode votar.");
        return contruirReposta(usuarioNaoPodeVotarException, HttpStatus.CONFLICT, request);
    }
    
    @ExceptionHandler(BasicException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Object> controlarBasicException(BasicException basicException, WebRequest request) {
        log.error(basicException.getMessage());
        return contruirReposta(basicException, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> controlarErroDesconhecido(Exception exception, WebRequest request) {
        log.error("Erro interno desconhecido, contacte o administrdor no ramal 5665", exception);
        return construirResposta(exception, "Unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<Object> contruirReposta(Exception exception,
                                                      HttpStatus httpStatus,
                                                      WebRequest request) {
        return construirResposta(exception, exception.getMessage(), httpStatus, request);
    }

    private ResponseEntity<Object> construirResposta(Exception exception,
                                                      String message,
                                                      HttpStatus httpStatus,
                                                      WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
        if (printStackTrace && isTraceOn(request)) {
            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));
        }
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    private boolean isTraceOn(WebRequest request) {
        String[] value = request.getParameterValues(TRACE);
        return Objects.nonNull(value)
                && value.length > 0
                && value[0].contentEquals("true");
    }

    @Override
    public ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        return contruirReposta(ex, status, request);
    }
}