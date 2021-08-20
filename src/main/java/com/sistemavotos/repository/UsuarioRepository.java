package com.sistemavotos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sistemavotos.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByCpf(String cpf);

	List<Usuario> findByNome(String nome);

	/*
	 * @Query("select u from Usuario u where u.cpf = :numCpf and u") Usuario
	 * findByEmailAddress(@Param("lastname") String lastname);
	 */
}
