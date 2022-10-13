package br.com.unext.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.dao.FormacaoAcademicaDao;
import br.com.unext.to.FormacaoAcademicaTo;

public class FormacaoAcademicaBo {
	
	private FormacaoAcademicaDao dao;
	
	public FormacaoAcademicaBo(Connection conexao) {
		dao = new FormacaoAcademicaDao(conexao);
	}
	
	public ArrayList<FormacaoAcademicaTo> listarByIdCandidato(int idCandidato) throws SQLException{
		return dao.listarByIdCandidato(idCandidato);
	}
}
