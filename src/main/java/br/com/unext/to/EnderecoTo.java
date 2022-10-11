package br.com.unext.to;

public class EnderecoTo {

	private int idEnderaco;
	private String logradouro;
	private int numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private String cep;
	private String uf;

	// Construtores
	public EnderecoTo() {

	}

	public EnderecoTo(int idEnderaco, String logradouro, int numero, String complemento, String bairro, String cidade,
			String cep, String uf) {
		super();
		this.idEnderaco = idEnderaco;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cep = cep;
		this.uf = uf;
	}

	public int getIdEnderaco() {
		return idEnderaco;
	}

	public void setIdEnderaco(int idEnderaco) {
		this.idEnderaco = idEnderaco;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}
