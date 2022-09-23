package br.com.forum.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.forum.model.Usuario;
import br.com.forum.service.TokenService;

public class AuthenticationTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	
	public AuthenticationTokenFilter(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = buscarTokenAuthetication(request);
		Boolean tokenIsValid = tokenService.ValidToken(token);
	
		if(tokenIsValid) {
			AutenticarViaToken(token);
		}
		
		filterChain.doFilter(request, response);
		
	}

	private void AutenticarViaToken(String token) {
		try {
			Usuario usuario = tokenService.RetornarUsuario(token);
			UsernamePasswordAuthenticationToken usuarioAuth = new UsernamePasswordAuthenticationToken(usuario.getId(), null, usuario.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(usuarioAuth);
		}catch(Exception e)
		{
			
		}
		
	}

	private String buscarTokenAuthetication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer")) {
			return null;
		}
		return token.substring(7, token.length());
	}

	
}
