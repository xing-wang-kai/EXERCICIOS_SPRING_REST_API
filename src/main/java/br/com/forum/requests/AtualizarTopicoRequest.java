package br.com.forum.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.forum.model.Topico;

public class AtualizarTopicoRequest {
	private Long id;
	@NotNull(message="O CAMPO 'TITULO' NÃO PODE SER NULO") 
	@NotEmpty(message="O CAMPO 'TITULO' NÃO PODE SER VÁZIO")  
	@NotBlank (message="O CAMPO 'TITULO' NÃO PODE SER ESTAR EM BRANCO")  
	@Length(min=5, message="O CAMPO 'TITULO' PRECISA TER NO MÍNIMO 5 CARACTERES")
	private String titulo;
	@NotNull(message="O CAMPO 'TITULO' NÃO PODE SER NULO") 
	@NotEmpty(message="O CAMPO 'TITULO' NÃO PODE SER VÁZIO")  
	@NotBlank (message="O CAMPO 'TITULO' NÃO PODE SER ESTAR EM BRANCO")  
	@Length(min=5, message="O CAMPO 'TITULO' PRECISA TER NO MÍNIMO 5 CARACTERES")
	private String mensagem;
	
	public Topico toTopico(Topico topico) {
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		return topico;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
