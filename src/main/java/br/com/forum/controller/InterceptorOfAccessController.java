package br.com.forum.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.forum.config.InterceptorOfAccess;
import br.com.forum.config.InterceptorOfAccess.Acesso;

@RestController
@RequestMapping("interceptor")
public class InterceptorOfAccessController {
	
	@GetMapping
	public ResponseEntity<List<Acesso>> verAcessos(){
		
		return ResponseEntity.ok(InterceptorOfAccess.acessos);
	}

}
