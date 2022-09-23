package br.com.forum.requests;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.com.forum.model.Usuario;

public class UsuarioRequest {

	private Long id;
	private String nome;
	private String email;
	private String senha;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Usuario toUsuario() {
		Usuario usuario = new Usuario();
		usuario.setNome(this.nome);
		usuario.setSenha(this.senha);
		usuario.setEmail(this.email);
		return usuario;
	}
	public UsernamePasswordAuthenticationToken toUserAuth() {
		
		return new UsernamePasswordAuthenticationToken(this.getEmail(), this.getSenha());
	}
	
}
