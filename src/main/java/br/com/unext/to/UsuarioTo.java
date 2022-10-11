package br.com.unext.to;

public class UsuarioTo {

	private int id;
	private String login;
	private String senha;

	// Construtores
	public UsuarioTo() {

	}

	public UsuarioTo(int id, String login, String senha) {
		this.id = id;
		this.login = login;
		this.senha = senha;
	}

	public boolean criar() {
		return false;
	}

	public boolean remover() {
		return false;
	}

	public boolean editarSenha() {
		return false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
