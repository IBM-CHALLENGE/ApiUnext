package br.com.unext.bo;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.unext.dao.PessoaDao;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.to.PessoaTo;

public class PessoaBo {
	
	private PessoaDao dao;
	
	public PessoaBo(Connection conexao) throws ClassNotFoundException, SQLException {
		dao = new PessoaDao(conexao);
	}
	
	public int cadastrarPessoa(PessoaTo pessoa) throws SQLException, ErroOperacaoException {
		return dao.cadastrar(pessoa);
	}

}
