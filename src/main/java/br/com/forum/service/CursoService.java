package br.com.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.forum.model.Curso;
import br.com.forum.repository.CursoRepository;

@Service
public class CursoService {

	@Autowired
	private CursoRepository cursoRepository;
	
	public Curso findCursoPorNome(String nome) {
		return this.cursoRepository.findByNome(nome);
	}
}
