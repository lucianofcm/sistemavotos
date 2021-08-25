# sistemavotos
Sistemas de votos sicred


#Introdução

Este projeto foi criado com a finaliade de implementar o que foi proposto no desafio técnico que tem como objetivo criar uma solução de back-end para gerenciar sessões de votação.

A solução desenvolvida consiste em:

- Camada de apresentação representada pelos controllers
- Camada de persistência utilizando hibernate/jpa representado pelos services e repositories.
- Camada de integração utilizando agentes de mensagem utilizando solução baseada no protocolo amqp

#Escolha da arquitetura

A escolha da arquiteura baseada em camadas se deu em função de se tratar de uma solução que deve expor serviços sendo assim a organização em camadas contribui para especializar os tipos de funções executadas por cada componente do software, criando um design altamente favorável a reutilização.
A escolha do framework spring foi a necessidade de otimização do tempo e em consequência o aumento da produtividade pois o mesmo oferece recursos para o processo de desenvolvimento de sofware.

![Imagem2](imagens/diagrama.png)

Como solicitado foi implementado uma cominicação baseada em evento utilizando o RabbitMQ como barramento de evento.

== Funcionalidade

- Cadastrar pauta
- Iniciar Votação
-- Valida se votação já foi inicia
-- Valida se votação já foi encerrada
- Votar




