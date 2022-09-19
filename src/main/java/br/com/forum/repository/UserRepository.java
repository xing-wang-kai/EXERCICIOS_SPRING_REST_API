package br.com.forum.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.forum.model.Usuario;

@Repository
public interface UserRepository extends CrudRepository<Usuario, Long> {


	Optional<Usuario> findByEmail(String email);
}



