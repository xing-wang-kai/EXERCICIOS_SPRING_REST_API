package br.com.forum.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.forum.dto.UsuarioDTO;
import br.com.forum.requests.UsuarioRequest;
import br.com.forum.service.UsuarioService;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("cadastrar")
	public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody UsuarioRequest usuario, UriComponentsBuilder UriBuilder)
	{
		this.usuarioService.cadastrar(usuario);
		
		UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
		
		URI uri = UriBuilder.path("/usuario/{id}").buildAndExpand(usuarioDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(usuarioDTO);
	}
}
