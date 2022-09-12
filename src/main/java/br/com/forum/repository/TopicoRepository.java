package br.com.forum.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.forum.model.Topico;

@Repository
public interface TopicoRepository extends CrudRepository<Topico, Long> {

	List<Topico> findByCurso_Nome(String nomeCurso);
}
