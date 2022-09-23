package br.com.forum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.forum.dto.TokenDTO;
import br.com.forum.requests.UsuarioRequest;
import br.com.forum.service.TokenService;

@RestController
@RequestMapping("auth")
public class AuthUsuarioController {
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> logar(@RequestBody @Valid UsuarioRequest usuarioRequest)
	{
		try {
			UsernamePasswordAuthenticationToken userAuth = usuarioRequest.toUserAuth();
			Authentication autenticatio = authManager.authenticate(userAuth);
			String token = tokenService.setToken(autenticatio);
			
			return ResponseEntity.ok(new TokenDTO(token,"Bearer"));
		}catch(AuthenticationException e)
		{
			return ResponseEntity.badRequest().build();
		}
		
	}
}
