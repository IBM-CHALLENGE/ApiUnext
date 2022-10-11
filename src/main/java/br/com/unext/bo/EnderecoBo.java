package br.com.unext.bo;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.unext.dao.EnderecoDao;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.to.EnderecoTo;

public class EnderecoBo {
	
	private EnderecoDao dao;
	
	public EnderecoBo(Connection conexao) throws ClassNotFoundException, SQLException {
		dao = new EnderecoDao(conexao);
	}

	public int cadastrarEndereco(EnderecoTo endereco) throws SQLException, ErroOperacaoException {
		return dao.cadastrar(endereco);
	}
}
