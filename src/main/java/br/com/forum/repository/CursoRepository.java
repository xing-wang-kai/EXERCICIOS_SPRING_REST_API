package br.com.forum.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.forum.model.Curso;

@Repository
public interface CursoRepository extends CrudRepository<Curso, Long>{

	Curso findByNome(String nome);

}
