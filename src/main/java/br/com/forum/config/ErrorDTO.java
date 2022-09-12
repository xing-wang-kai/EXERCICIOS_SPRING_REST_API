package br.com.forum.config;

public class ErrorDTO {
	
	public String campo;
	public String error;
	
	public ErrorDTO(String campo, String error) {
		super();
		this.campo = campo;
		this.error = error;
	}
	
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
}
