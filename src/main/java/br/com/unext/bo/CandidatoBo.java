package br.com.unext.bo;

import java.sql.SQLException;

import br.com.unext.dao.CandidatoDao;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.factory.ConnectionFactory;
import br.com.unext.to.CandidatoTo;

public class CandidatoBo {
	
	private CandidatoDao dao;
	
	public CandidatoBo() throws ClassNotFoundException, SQLException {
		dao = new CandidatoDao(ConnectionFactory.getConnection());
	}
	
	public int cadastrarCandidato(CandidatoTo candidato) throws SQLException, ErroOperacaoException {
		return dao.cadastrar(candidato);
	}

}
