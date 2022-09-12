package br.com.forum.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.forum.dto.AtualizarTopicoDTO;
import br.com.forum.dto.TopicoDTO;
import br.com.forum.model.Curso;
import br.com.forum.model.Topico;
import br.com.forum.repository.TopicoRepository;
import br.com.forum.requests.AtualizarTopicoRequest;
import br.com.forum.requests.TopicoRequest;

@Service
public class TopicoService {
	@Autowired
	private TopicoRepository topicoRepository;
	
	@Autowired
	private CursoService cursoService;
	
	public List<TopicoDTO> buscarTodos(){
		List<Topico> topicos = (List<Topico>) this.topicoRepository.findAll();
		return TopicoDTO.toListTopicoDTO(topicos);
	}
	
	public TopicoDTO buscarPorId(Long id) {
		Optional<Topico> topico = this.topicoRepository.findById(id);
		TopicoDTO dto = new TopicoDTO();
		topico.stream().forEach(t -> dto.toTopicoDTO(t));
		return dto;
	}
	
	public List<TopicoDTO> findByCursoNome(String nome){
		List<Topico> topicos = (List<Topico>) this.topicoRepository.findByCurso_Nome(nome);
		topicos.stream().forEach(t -> System.out.println(t.getTitulo()));
		System.out.println(topicos.isEmpty());
		return TopicoDTO.toListTopicoDTO(topicos);
	}
	
	public Topico cadastrarTopico(TopicoRequest tr) {
		
		Curso curso = this.cursoService.findCursoPorNome(tr.getNomeCurso());
		Topico topico = new Topico(tr.getTitulo(),
									tr.getMensagem(),
									curso);
		this.topicoRepository.save(topico);
		return(Topico) this.topicoRepository.findByCurso_Nome(tr.getNomeCurso());
	}
	
	public AtualizarTopicoDTO atualizarTopico(Long id, AtualizarTopicoRequest topicoRequest) {
		
		Optional<Topico> topicos = this.topicoRepository.findById(id);
		Topico topico = topicos.get();
		
		topico.setMensagem(topicoRequest.getMensagem());
		topico.setTitulo(topicoRequest.getTitulo());
		
		this.topicoRepository.save(topico);
		
		AtualizarTopicoDTO topicoDto = new AtualizarTopicoDTO(topico);
		
		return topicoDto;
	}
	
	public void deletarTopico(Long id) {
		this.topicoRepository.deleteById(id);
	}
}
