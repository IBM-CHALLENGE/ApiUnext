package br.com.unext.to;

public class PessoaTo {

	private int idPessoa;
	private UsuarioTo usuario;
	private String nome;
	private String rg;
	private String cpf;
	private String dataNascimento;
	private char sexo;
	private String urlFoto;

	// Construtores
	public PessoaTo() {

	}

	public PessoaTo(int idPessoa, String nome, String rg, String cpf, String dataNascimento, char sexo,
			String urlFoto, UsuarioTo usuario) {
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.rg = rg;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.urlFoto = urlFoto;
		this.usuario = usuario;
	}

	public int getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(int idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public UsuarioTo getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioTo usuario) {
		this.usuario = usuario;
	}
}
