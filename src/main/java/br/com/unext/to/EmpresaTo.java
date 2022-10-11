package br.com.unext.to;

import java.util.ArrayList;

public class EmpresaTo {

	private int id;
	private UsuarioTo usuario;
	private String nome;
	private String razaoSocial;
	private String fotoEmpresa;
	private String cnpj;
	private String atuacao;
	private String descricao;
	private char status;
	private ArrayList<EnderecoTo> enderecos;

	// Construtores
	public EmpresaTo() {

	}

	public EmpresaTo(int id, UsuarioTo usuario, String nome, String razaoSocial, String fotoEmpresa, String cnpj,
			String atuacao, String descricao, char status, ArrayList<EnderecoTo> enderecos) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.nome = nome;
		this.razaoSocial = razaoSocial;
		this.fotoEmpresa = fotoEmpresa;
		this.cnpj = cnpj;
		this.atuacao = atuacao;
		this.descricao = descricao;
		this.status = status;
		this.enderecos = enderecos;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UsuarioTo getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioTo usuario) {
		this.usuario = usuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getFotoEmpresa() {
		return fotoEmpresa;
	}

	public void setFotoEmpresa(String fotoEmpresa) {
		this.fotoEmpresa = fotoEmpresa;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getAtuacao() {
		return atuacao;
	}

	public void setAtuacao(String atuacao) {
		this.atuacao = atuacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public ArrayList<EnderecoTo> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(ArrayList<EnderecoTo> enderecos) {
		this.enderecos = enderecos;
	}

}
