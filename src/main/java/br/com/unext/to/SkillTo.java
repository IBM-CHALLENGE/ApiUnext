package br.com.unext.to;

public class SkillTo {

	private int id;
	private String descricao;
	private char tipo;

	// Construtores
	public SkillTo() {

	}

	public SkillTo(int id, String descricao, char tipo) {
		this.id = id;
		this.descricao = descricao;
		this.tipo = tipo;
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

	public char gettipo() {
		return tipo;
	}

	public void settipo(char tipo) {
		this.tipo = tipo;

	}

}
