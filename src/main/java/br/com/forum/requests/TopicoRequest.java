package br.com.forum.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class TopicoRequest {
	
	@NotNull(message="O CAMPO 'TITULO' NÃO PODE SER NULO") 
	@NotEmpty(message="O CAMPO 'TITULO' NÃO PODE SER VÁZIO")  
	@NotBlank (message="O CAMPO 'TITULO' NÃO PODE SER ESTAR EM BRANCO")  
	@Length(min=5, message="O CAMPO 'TITULO' PRECISA TER NO MÍNIMO 5 CARACTERES")
	private String titulo;
	@NotNull @NotEmpty @NotBlank @Length(min=5)
	private String mensagem;
	@NotNull @NotEmpty @NotBlank @Length(min=5)
	private String nomeCurso;
	
	public TopicoRequest(String titulo, String mensagem, String nomeCurso) {
		this.setTitulo(titulo);
		this.setMensagem(mensagem);
		this.setNomeCurso(nomeCurso);
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String messagem) {
		this.mensagem = messagem;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
}
