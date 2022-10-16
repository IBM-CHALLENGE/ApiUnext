package br.com.unext.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.dao.FormacaoAcademicaDao;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.to.FormacaoAcademicaTo;

public class FormacaoAcademicaBo {
	
	private FormacaoAcademicaDao dao;
	
	public FormacaoAcademicaBo(Connection conexao) {
		this.dao = new FormacaoAcademicaDao(conexao);
	}
	
	public int cadastrar(FormacaoAcademicaTo formacao) throws SQLException, ErroOperacaoException {
		return dao.cadastrar(formacao);
	}
	
	public boolean remover(int id) throws SQLException, ErroOperacaoException {
		return dao.remover(id);
	}
	
	public ArrayList<FormacaoAcademicaTo> listarByIdCandidato(int idCandidato) throws SQLException{
		return dao.listarByIdCandidato(idCandidato);
	}
}
