package br.com.unext.to;

public class FormacaoAcademicaTo {

	private int id;
	private String curso;
	private String grauAcademico;
	private String instituicao;
	private String dataInicio;
	private String dataFim;

	// Construtores
	public FormacaoAcademicaTo() {

	}

	public FormacaoAcademicaTo(int id, String curso, String grauAcademico, String dataInicio, String dataFim,
			String instituicao) {
		super();
		this.id = id;
		this.curso = curso;
		this.grauAcademico = grauAcademico;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.instituicao = instituicao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getGrauAcademico() {
		return grauAcademico;
	}

	public void setGrauAcademico(String grauAcademico) {
		this.grauAcademico = grauAcademico;
	}

	public String getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(String dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getDataFim() {
		return dataFim;
	}

	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

}
