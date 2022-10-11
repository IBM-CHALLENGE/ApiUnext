package br.com.unext.to;

public class ContatoTo {

	private int id;
	private int idPessoa;
	private String email;
	private String telefone;

	// Construtores
	public ContatoTo() {

	}

	public ContatoTo(int id, int idPessoa, String email, String telefone) {
		super();
		this.id = id;
		this.idPessoa = idPessoa;
		this.email = email;
		this.telefone = telefone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
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