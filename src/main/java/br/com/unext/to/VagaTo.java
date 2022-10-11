package br.com.unext.to;

import java.util.Date;
import java.util.List;

public class VagaTo {

	private int id;
	private EmpresaTo empresa;
	private String descricao;
	private Date dataCadastrado;
	private Date dataEncerramento;
	private String cargo;
	private double salario;
	private List<CandidaturaTo> candidaturas;
	private List<SkillTo> skillDesejadas;
	private int qtdVagas;

	// Construtores
	public VagaTo() {

	}

	public VagaTo(int id, String descricao, Date dataCadastrado, Date dataEncerramento, double salario,
			EmpresaTo empresa, List<CandidaturaTo> candidaturas, List<SkillTo> skillDesejadas, int qtdVagas) {
		this.id = id;
		this.descricao = descricao;
		this.dataCadastrado = dataCadastrado;
		this.dataEncerramento = dataEncerramento;
		this.salario = salario;
		this.empresa = empresa;
		this.candidaturas = candidaturas;
		this.skillDesejadas = skillDesejadas;
		this.qtdVagas = qtdVagas;
	}

	public EmpresaTo getRecrutador() {
		return empresa;
	}

	public void setEmpresa(EmpresaTo empresa) {
		this.empresa = empresa;
	}

	public List<SkillTo> getSkillDesejadas() {
		return skillDesejadas;
	}

	public void setSkillDesejadas(List<SkillTo> skillDesejadas) {
		this.skillDesejadas = skillDesejadas;
	}

	public List<CandidaturaTo> getCandidaturas() {
		return candidaturas;
	}

	public void setCandidaturas(List<CandidaturaTo> candidaturas) {
		this.candidaturas = candidaturas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataCadastrado() {
		return dataCadastrado;
	}

	public void setDataCadastrado(Date dataCadastrado) {
		this.dataCadastrado = dataCadastrado;
	}

	public Date getDataEncerramento() {
		return dataEncerramento;
	}

	public void setDataEncerramento(Date dataEncerramento) {
		this.dataEncerramento = dataEncerramento;
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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}