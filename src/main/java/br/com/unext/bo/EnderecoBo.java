package br.com.unext.bo;

import java.sql.SQLException;

import br.com.unext.dao.EnderecoDao;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.factory.ConnectionFactory;
import br.com.unext.to.EnderecoTo;

public class EnderecoBo {
	
	private EnderecoDao dao;
	
	public EnderecoBo() throws ClassNotFoundException, SQLException {
		dao = new EnderecoDao(ConnectionFactory.getConnection());
	}

	public int cadastrarEndereco(EnderecoTo endereco) throws SQLException, ErroOperacaoException {
		return dao.cadastrar(endereco);
	}
}
