package br.com.unext.to;

public class ContatoTo {

	private int id;
	private String email;
	private String telefone;

	// Construtores
	public ContatoTo() {

	}

	public ContatoTo(int id, String email, String telefone) {
		super();
		this.id = id;
		this.email = email;
		this.telefone = telefone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}