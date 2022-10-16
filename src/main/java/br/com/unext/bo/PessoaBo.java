package br.com.unext.bo;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.unext.dao.PessoaDao;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.NaoEncontradoException;
import br.com.unext.to.PessoaTo;

public class PessoaBo {
	
	private PessoaDao dao;
	
	public PessoaBo(Connection conexao) throws ClassNotFoundException, SQLException {
		this.dao = new PessoaDao(conexao);
	}
	
	public int cadastrarPessoa(PessoaTo pessoa) throws SQLException, ErroOperacaoException {
		return dao.cadastrar(pessoa);
	}
	
	public boolean atualizarPessoa(PessoaTo pessoa) throws SQLException, ErroOperacaoException {
		return dao.editar(pessoa);
	}

	public int buscarIdPessoaByIdCandidato(int idCandidato) throws SQLException, NaoEncontradoException{
		return dao.buscarIdPessoaByIdCandidato(idCandidato);
	}
}
