package br.com.unext.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.NaoEncontradoException;
import br.com.unext.to.CandidaturaTo;

public class CandidaturaDao implements IDao<CandidaturaTo>{

	private Connection conexao;

	public CandidaturaDao(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public int cadastrar(CandidaturaTo model) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean editar(CandidaturaTo model) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remover(int id) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<CandidaturaTo> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CandidaturaTo buscarById(int id) throws SQLException, ErroOperacaoException, NaoEncontradoException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
