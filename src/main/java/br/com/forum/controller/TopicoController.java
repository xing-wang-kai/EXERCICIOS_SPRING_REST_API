package br.com.forum.controller;

import java.net.URI;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.forum.dto.AtualizarTopicoDTO;
import br.com.forum.dto.TopicoDTO;
import br.com.forum.model.Topico;
import br.com.forum.requests.AtualizarTopicoRequest;
import br.com.forum.requests.TopicoRequest;
import br.com.forum.service.TopicoService;

@RestController
@RequestMapping("/topico")
public class TopicoController {
	
	@Autowired
	public TopicoService topicoService;

	@GetMapping
	@Cacheable(value="rota autenticação")
	public List<TopicoDTO> buscarTodos(@RequestParam(required=false) String nomeCurso){
		if(nomeCurso == null) {
			return this.topicoService.buscarTodos();
		}
		else {
			String novoNomeCurso = nomeCurso.replace("+", " ");
			return this.topicoService.findByCursoNome(novoNomeCurso);
		}		
	}
	
	@GetMapping("/{id}")
	@CacheEvict(value="rota autenticação")
	public TopicoDTO buscarPorId(@PathVariable("id") Long id) {
		
		return this.topicoService.buscarPorId(id);
	}
	
	@PostMapping("/cadastrar")
	@Transactional
	public ResponseEntity<TopicoDTO> salvarTopico(@RequestBody @Valid TopicoRequest topicoRequest, UriComponentsBuilder UriBuilder) {
		
		try {
	
			Topico topico = this.topicoService.cadastrarTopico(topicoRequest);
			
			URI URI = UriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
			return ResponseEntity.created(URI).body(new TopicoDTO(topico));
			
		}catch(Exception err)
		{
			System.out.println("ERR: " + err.getMessage());
			return null;
		}
		
	}
	
	@PostMapping("/{id}")
	@Transactional
	public ResponseEntity<AtualizarTopicoDTO> atualizarTopico(@PathVariable("id") Long id, @RequestBody @Valid AtualizarTopicoRequest topicoReq) {
		
		try {
			
			AtualizarTopicoDTO topicoDto = this.topicoService.atualizarTopico(id, topicoReq);
			return ResponseEntity.ok(topicoDto);
			
		}catch(Exception err){
			System.out.println("erro: " + err.getMessage());
			return null;
		}
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deletarTopico(@PathVariable("id") Long id){
		
		this.topicoService.deletarTopico(id);		
		
		return ResponseEntity.ok().build();
	}
}
