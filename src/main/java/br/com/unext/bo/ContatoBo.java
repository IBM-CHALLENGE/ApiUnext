package br.com.unext.bo;

import java.sql.SQLException;

import br.com.unext.dao.ContatoDao;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.factory.ConnectionFactory;
import br.com.unext.to.ContatoTo;

public class ContatoBo {

	private ContatoDao dao;
	
	public ContatoBo() throws ClassNotFoundException, SQLException {
		dao = new ContatoDao(ConnectionFactory.getConnection());
	}
	
	public int cadastrarContato(ContatoTo contato) throws SQLException, ErroOperacaoException {
		return dao.cadastrar(contato);
	}
}
