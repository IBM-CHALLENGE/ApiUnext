package br.com.unext.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.NaoEncontradoException;
import br.com.unext.to.CandidatoTo;
import br.com.unext.to.CandidaturaTo;
import br.com.unext.to.VagaTo;

public class CandidaturaDao implements IDao<CandidaturaTo>{

	private Connection conexao;

	public CandidaturaDao(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public int cadastrar(CandidaturaTo model) throws SQLException, ErroOperacaoException {
		String query = "INSERT INTO T_UNEXT_CANDIDATURA(ID_CANDIDATURA,ID_STATUS,ID_CANDIDATO,ID_VAGA,DT_CADASTRO) "
					+ "VALUES (sq_t_unext_candidatura.nextval, ?, ?, ?, SYSDATE)";
		
		PreparedStatement stm = conexao.prepareStatement(query, new String[] {"ID_CANDIDATURA"});
		
		stm.setInt(1, 1);
		stm.setInt(2, model.getCandidato().getIdCandidato());
		stm.setInt(3, model.getVaga().getId());
		
		stm.executeUpdate();
		
		ResultSet result = stm.getGeneratedKeys();
		if(result.next()) {
			model.setId(result.getInt(1));
			return model.getId();
		}
		
		throw new ErroOperacaoException("Nao foi possivel cadastrar a candidatura");
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
	
	public ArrayList<CandidaturaTo> buscarByIdVaga(int idVaga) throws SQLException{
		ArrayList<CandidaturaTo> candidaturas = new ArrayList<CandidaturaTo>();
		
		String query = "SELECT  "
					+ "    T_UNEXT_CANDIDATURA.ID_CANDIDATURA ID_CANDIDATURA, "
					+ "    T_UNEXT_CANDIDATURA.ID_CANDIDATO ID_CANDIDATO, "
					+ "    TO_CHAR(T_UNEXT_CANDIDATURA.DT_CADASTRO, 'DD/MM/YYYY') DT_CADASTRO, "
					+ "    T_UNEXT_CANDIDATURA.DS_FEEDBACK DS_FEEDBACK, "
					+ "    T_UNEXT_STATUS.DS_DESCRICAO DS_STATUS, "
					+ "    T_UNEXT_VAGA.ID_VAGA ID_VAGA "
					+ "FROM  "
					+ "    T_UNEXT_CANDIDATURA "
					+ "    INNER JOIN T_UNEXT_VAGA "
					+ "        ON T_UNEXT_CANDIDATURA.ID_VAGA = T_UNEXT_VAGA.ID_VAGA "
					+ "    INNER JOIN T_UNEXT_STATUS  "
					+ "        ON T_UNEXT_CANDIDATURA.ID_STATUS = T_UNEXT_STATUS.ID_STATUS "
					+ "WHERE "
					+ "    T_UNEXT_VAGA.ST_STATUS = 'A' "
					+ "    AND T_UNEXT_VAGA.ID_VAGA = ?";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setInt(1, idVaga);
		
		ResultSet result = stm.executeQuery();
		while(result.next()) {
			CandidaturaTo candidatura = new CandidaturaTo();
			VagaTo vaga = new VagaTo();
			CandidatoTo candidato = new CandidatoTo();
			
			
			
			candidatura.setId(result.getInt("ID_CANDIDATURA"));
			candidatura.setDataCadastro(result.getString("DT_CADASTRO"));
			candidatura.setFeedback(result.getString("DS_FEEDBACK"));
			candidatura.setStatus(result.getString("DS_STATUS"));
			
			candidato.setIdCandidato(result.getInt("ID_CANDIDATO"));
			vaga.setId(result.getInt("ID_VAGA"));
			
			candidatura.setCandidato(candidato);
			candidatura.setVaga(vaga);
			
			candidaturas.add(candidatura);
		}
		
		return candidaturas;
	}
	
	public ArrayList<CandidaturaTo> buscarByIdCandidato(int idCandidato) throws SQLException{
		ArrayList<CandidaturaTo> candidaturas = new ArrayList<CandidaturaTo>();
		
		String query = "SELECT  "
					+ "    T_UNEXT_CANDIDATURA.ID_CANDIDATURA ID_CANDIDATURA, "
					+ "    T_UNEXT_CANDIDATURA.ID_CANDIDATO ID_CANDIDATO, "
					+ "    TO_CHAR(T_UNEXT_CANDIDATURA.DT_CADASTRO, 'DD/MM/YYYY') DT_CADASTRO, "
					+ "    T_UNEXT_CANDIDATURA.DS_FEEDBACK DS_FEEDBACK, "
					+ "    T_UNEXT_STATUS.DS_DESCRICAO DS_STATUS, "
					+ "    T_UNEXT_VAGA.ID_VAGA ID_VAGA "
					+ "FROM  "
					+ "    T_UNEXT_CANDIDATURA "
					+ "    INNER JOIN T_UNEXT_VAGA "
					+ "        ON T_UNEXT_CANDIDATURA.ID_VAGA = T_UNEXT_VAGA.ID_VAGA "
					+ "    INNER JOIN T_UNEXT_STATUS  "
					+ "        ON T_UNEXT_CANDIDATURA.ID_STATUS = T_UNEXT_STATUS.ID_STATUS "
					+ "WHERE "
					+ "    T_UNEXT_VAGA.ST_STATUS = 'A' "
					+ "    AND T_UNEXT_CANDIDATURA.ID_CANDIDATO = ?";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setInt(1, idCandidato);
		
		ResultSet result = stm.executeQuery();
		while(result.next()) {
			CandidaturaTo candidatura = new CandidaturaTo();
			VagaTo vaga = new VagaTo();
			CandidatoTo candidato = new CandidatoTo();
			
			
			
			candidatura.setId(result.getInt("ID_CANDIDATURA"));
			candidatura.setDataCadastro(result.getString("DT_CADASTRO"));
			candidatura.setFeedback(result.getString("DS_FEEDBACK"));
			candidatura.setStatus(result.getString("DS_STATUS"));
			
			candidato.setIdCandidato(result.getInt("ID_CANDIDATO"));
			vaga.setId(result.getInt("ID_VAGA"));
			
			candidatura.setCandidato(candidato);
			candidatura.setVaga(vaga);
			
			candidaturas.add(candidatura);
		}
		
		return candidaturas;
	}

	public ArrayList<CandidaturaTo> buscarByIdEmpresa(int idEmpresa) throws SQLException{
		ArrayList<CandidaturaTo> candidaturas = new ArrayList<CandidaturaTo>();
		
		String query = "SELECT  "
					+ "    T_UNEXT_CANDIDATURA.ID_CANDIDATURA ID_CANDIDATURA, "
					+ "    T_UNEXT_CANDIDATURA.ID_CANDIDATO ID_CANDIDATO, "
					+ "    TO_CHAR(T_UNEXT_CANDIDATURA.DT_CADASTRO, 'DD/MM/YYYY') DT_CADASTRO, "
					+ "    T_UNEXT_CANDIDATURA.DS_FEEDBACK DS_FEEDBACK, "
					+ "    T_UNEXT_STATUS.DS_DESCRICAO DS_STATUS, "
					+ "    T_UNEXT_VAGA.ID_VAGA ID_VAGA "
					+ "FROM  "
					+ "    T_UNEXT_CANDIDATURA "
					+ "    INNER JOIN T_UNEXT_VAGA "
					+ "        ON T_UNEXT_CANDIDATURA.ID_VAGA = T_UNEXT_VAGA.ID_VAGA "
					+ "    INNER JOIN T_UNEXT_STATUS  "
					+ "        ON T_UNEXT_CANDIDATURA.ID_STATUS = T_UNEXT_STATUS.ID_STATUS "
					+ "WHERE "
					+ "    T_UNEXT_VAGA.ST_STATUS = 'A' "
					+ "    AND T_UNEXT_VAGA.ID_EMPRESA = ?";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setInt(1, idEmpresa);
		
		ResultSet result = stm.executeQuery();
		while(result.next()) {
			CandidaturaTo candidatura = new CandidaturaTo();
			VagaTo vaga = new VagaTo();
			CandidatoTo candidato = new CandidatoTo();
			
			
			
			candidatura.setId(result.getInt("ID_CANDIDATURA"));
			candidatura.setDataCadastro(result.getString("DT_CADASTRO"));
			candidatura.setFeedback(result.getString("DS_FEEDBACK"));
			candidatura.setStatus(result.getString("DS_STATUS"));
			
			candidato.setIdCandidato(result.getInt("ID_CANDIDATO"));
			vaga.setId(result.getInt("ID_VAGA"));
			
			candidatura.setCandidato(candidato);
			candidatura.setVaga(vaga);
			
			candidaturas.add(candidatura);
		}
		
		return candidaturas;
	}
}
