package br.com.unext.to;

import java.util.ArrayList;

public class VagaTo {

	private int id;
	private EmpresaTo empresa;
	private String descricao;
	private String dataCadastrado;
	private String dataEncerramento;
	private String cargo;
	private double salario;
	private int qtdVagas;
	private char status;
	private ArrayList<CandidaturaTo> candidaturas;
	private ArrayList<SkillTo> skillsDesejadas;

	// Construtores
	public VagaTo() {

	}

	public VagaTo(int id, EmpresaTo empresa, String descricao, String dataCadastrado, String dataEncerramento,
			String cargo, double salario, int qtdVagas, char status, ArrayList<CandidaturaTo> candidaturas,
			ArrayList<SkillTo> skillsDesejadas) {
		super();
		this.id = id;
		this.empresa = empresa;
		this.descricao = descricao;
		this.dataCadastrado = dataCadastrado;
		this.dataEncerramento = dataEncerramento;
		this.cargo = cargo;
		this.salario = salario;
		this.qtdVagas = qtdVagas;
		this.status = status;
		this.candidaturas = candidaturas;
		this.skillsDesejadas = skillsDesejadas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EmpresaTo getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaTo empresa) {
		this.empresa = empresa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataCadastrado() {
		return dataCadastrado;
	}

	public void setDataCadastrado(String dataCadastrado) {
		this.dataCadastrado = dataCadastrado;
	}

	public String getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(String dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public int getQtdVagas() {
		return qtdVagas;
	}

	public void setQtdVagas(int qtdVagas) {
		this.qtdVagas = qtdVagas;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public ArrayList<CandidaturaTo> getCandidaturas() {
		return candidaturas;
	}

	public void setCandidaturas(ArrayList<CandidaturaTo> candidaturas) {
		this.candidaturas = candidaturas;
	}

	public ArrayList<SkillTo> getSkillsDesejadas() {
		return skillsDesejadas;
	}

	public void setSkillsDesejadas(ArrayList<SkillTo> skillsDesejadas) {
		this.skillsDesejadas = skillsDesejadas;
	}

}