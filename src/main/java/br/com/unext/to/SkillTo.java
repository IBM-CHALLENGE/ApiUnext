package br.com.unext.to;

public class SkillTo {

	private int id;
	private String descricao;
	private char tipo;
	private int nivel;

	// Construtores
	public SkillTo() {

	}

	public SkillTo(int id, String descricao, char tipo, int nivel) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.tipo = tipo;
		this.nivel = nivel;
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

	public char getTipo() {
		return tipo;
	}

	public void setTipo(char tipo) {
		this.tipo = tipo;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

}
