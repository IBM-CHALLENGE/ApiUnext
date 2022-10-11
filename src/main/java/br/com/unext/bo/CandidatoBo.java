package br.com.unext.bo;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.unext.dao.CandidatoDao;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.to.CandidatoTo;

public class CandidatoBo {
	
	private CandidatoDao dao;
	
	public CandidatoBo(Connection conexao) throws ClassNotFoundException, SQLException {
		dao = new CandidatoDao(conexao);
	}
	
	public int cadastrarCandidato(CandidatoTo candidato) throws SQLException, ErroOperacaoException {
		return dao.cadastrar(candidato);
	}

}
