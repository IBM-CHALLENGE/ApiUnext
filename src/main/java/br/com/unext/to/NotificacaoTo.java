package br.com.unext.to;

import java.util.Date;

public class NotificacaoTo {

	private int id;
	private String tituloNotificacao;
	private String descricaoNotificacao;
	private Date dataCadastro;
	private CandidaturaTo candidatura;

	public NotificacaoTo() {

	}

	public NotificacaoTo(int id, String tituloNotificacao, String descricaoNotificacao, Date dataCadastro,
			CandidaturaTo candidatura) {
		this.id = id;
		this.tituloNotificacao = tituloNotificacao;
		this.descricaoNotificacao = descricaoNotificacao;
		this.dataCadastro = dataCadastro;
		this.candidatura = candidatura;
	}

	public int getId() {
		return id;
	}

	public String getTituloNotificacao() {
		return tituloNotificacao;
	}

	public String getDescricaoNotificacao() {
		return descricaoNotificacao;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTituloNotificacao(String tituloNotificacao) {
		this.tituloNotificacao = tituloNotificacao;
	}

	public void setDescricaoNotificacao(String descricaoNotificacao) {
		this.descricaoNotificacao = descricaoNotificacao;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public CandidaturaTo getCandidatura() {
		return candidatura;
	}

	public void setCandidatura(CandidaturaTo candidatura) {
		this.candidatura = candidatura;
	}

}
