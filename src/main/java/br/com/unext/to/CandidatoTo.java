package br.com.unext.to;

import java.util.List;

public class CandidatoTo extends PessoaTo {

	private int idCandidato;
	private String escolaridade;
	private List<ContatoTo> contatos;
	private String atuacao;
	private char status;
	private List<SkillTo> skills;
	private EnderecoTo endereco;
	private List<FormacaoAcademicaTo> formacoesAcademicas;

	// Construtores
	public CandidatoTo() {
		// TODO Auto-generated constructor stub
	}

	public CandidatoTo(int idCandidato, String escolaridade, List<ContatoTo> contatos, List<SkillTo> skills,
			EnderecoTo endereco, List<FormacaoAcademicaTo> formacoesAcademicas, String atuacao, char status,
			int idPessoa, String nome, String rg, String cpf, String dataNascimento, char sexo, String urlFoto,
			UsuarioTo usuario) {
		super(idPessoa, nome, rg, cpf, dataNascimento, sexo, urlFoto, usuario);
		this.idCandidato = idCandidato;
		this.escolaridade = escolaridade;
		this.contatos = contatos;
		this.skills = skills;
		this.endereco = endereco;
		this.formacoesAcademicas = formacoesAcademicas;
		this.atuacao = atuacao;
		this.status = status;
	}

	public int getIdCandidato() {
		return idCandidato;
	}

	public void setIdCandidato(int idCandidato) {
		this.idCandidato = idCandidato;
	}

	public String getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(String escolaridade) {
		this.escolaridade = escolaridade;
	}

	public List<ContatoTo> getContatos() {
		return contatos;
	}

	public void setContatos(List<ContatoTo> contatos) {
		this.contatos = contatos;
	}

	public List<SkillTo> getSkills() {
		return skills;
	}

	public void setSkills(List<SkillTo> skills) {
		this.skills = skills;
	}

	public EnderecoTo getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoTo endereco) {
		this.endereco = endereco;
	}

	public List<FormacaoAcademicaTo> getFormacoesAcademicas() {
		return formacoesAcademicas;
	}

	public void setFormacoesAcademicas(List<FormacaoAcademicaTo> formacoesAcademicas) {
		this.formacoesAcademicas = formacoesAcademicas;
	}

	public String getAtuacao() {
		return atuacao;
	}

	public void setAtuacao(String atuacao) {
		this.atuacao = atuacao;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

}