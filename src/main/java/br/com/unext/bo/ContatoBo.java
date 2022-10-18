package br.com.unext.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.dao.ContatoDao;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.NaoEncontradoException;
import br.com.unext.to.ContatoTo;

public class ContatoBo {

	private ContatoDao dao;
	
	public ContatoBo(Connection conexao) throws ClassNotFoundException, SQLException {
		this.dao = new ContatoDao(conexao);
	}
	
	public int cadastrarContato(ContatoTo contato) throws SQLException, ErroOperacaoException {
		return dao.cadastrar(contato);
	}
	
	public boolean removerContato(int id) throws SQLException, ErroOperacaoException {
		return dao.remover(id);
	}
	
	public ArrayList<ContatoTo> listarByIdPessoa(int idPessoa) throws SQLException, ErroOperacaoException, NaoEncontradoException{
		return dao.listarByIdPessoa(idPessoa);
	}
}
