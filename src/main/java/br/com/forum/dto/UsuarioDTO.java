package br.com.forum.dto;

import br.com.forum.requests.UsuarioRequest;

public class UsuarioDTO {
	
	private Long id;
	private String nome;
	private String email;
	private String senha;
	
	public UsuarioDTO(UsuarioRequest usuario) {
		this.setId(usuario.getId());
		this.setNome(usuario.getNome());
		this.setEmail(usuario.getEmail());
		this.setSenha(usuario.getSenha());
	}
	
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
}
