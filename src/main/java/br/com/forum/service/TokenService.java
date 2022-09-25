package br.com.forum.service;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.forum.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${forum.jwt.secret}")
	private String secret;
	
	@Value("${forum.jwt.expiration}")
	private String expiration;
	
	@Autowired
	private UsuarioService usuarioService;

	public String setToken(Authentication autenticatio) {
		 
		Usuario usuario = (Usuario) autenticatio.getPrincipal();
		Date hoje = new Date();
		Date Expiration = new Date(hoje.getTime() + Integer.parseInt(expiration));
		return Jwts.builder()
					.setIssuer("form.login.user.token")
					.setSubject(usuario.getId().toString())
					.setIssuedAt(hoje)
					.setExpiration(Expiration)
					.signWith(SignatureAlgorithm.HS256, secret)
					.compact();
	}

	public Boolean ValidToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
		
	}

	public Usuario RetornarUsuario(String token) {
		
		
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		Long id = Long.parseLong(claims.getSubject());
		
		return usuarioService.buscarUsuarioPorId(id);
		
	}

}
