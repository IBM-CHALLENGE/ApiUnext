package br.com.unext.to;

import java.util.Date;

public class CandidaturaTo {
	
	private int id;
	private VagaTo vaga;
	private CandidatoTo candidato;
	private int porcentagemMatch;
	private Date dataCadastro;
	private String feedback;
	private String status;
		
	
	
	//Construtores
	public CandidaturaTo() {
		
	}

	public CandidaturaTo(int id, VagaTo vaga, CandidatoTo candidato, int porcentagemMatch, Date dataCadastro,String feedback, String status) {
		this.id = id;
		this.vaga = vaga;
		this.candidato = candidato;
		this.porcentagemMatch = porcentagemMatch;
		this.dataCadastro = dataCadastro;
		this.feedback = feedback;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public VagaTo getVaga() {
		return vaga;
	}
	
	public void setVaga(VagaTo vaga) {
		this.vaga = vaga;
	}
	
	public CandidatoTo getCandidato() {
		return candidato;
	}
	
	public void setCandidato(CandidatoTo candidato) {
		this.candidato = candidato;
	}
	
	public int getPorcentagemMatch() {
		return porcentagemMatch;
	}
	
	public void setPorcentagemMatch(int porcentagemMatch) {
		this.porcentagemMatch = porcentagemMatch;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}
	
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
		
	public String getFeedback() {
	return feedback;
	}

	public void setFeeback(String feedback) {
	this.feedback = feedback;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
