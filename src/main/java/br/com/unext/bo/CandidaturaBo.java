package br.com.unext.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.dao.CandidaturaDao;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.to.CandidaturaTo;

public class CandidaturaBo {

	private CandidaturaDao dao;
	
	public CandidaturaBo(Connection conexao) throws ClassNotFoundException, SQLException {
		this.dao = new CandidaturaDao(conexao);
	}
	
	public int cadastrar(CandidaturaTo candidatura) throws SQLException, ErroOperacaoException {
		return dao.cadastrar(candidatura);
	}
	
	public ArrayList<CandidaturaTo> buscarByIdVaga(int idVaga) throws SQLException{
		return dao.buscarByIdVaga(idVaga);
	}

	public ArrayList<CandidaturaTo> buscarByIdCandidato(int idCandidato) throws SQLException{
		return dao.buscarByIdCandidato(idCandidato);
	}
	
	public ArrayList<CandidaturaTo> buscarByIdEmpresa(int idEmpresa) throws SQLException{
		return dao.buscarByIdEmpresa(idEmpresa);
	}
}
