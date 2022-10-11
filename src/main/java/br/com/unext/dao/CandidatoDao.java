package br.com.unext.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.to.CandidatoTo;

public class CandidatoDao implements IDao<CandidatoTo> {

	private Connection conexao;

	public CandidatoDao(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public int cadastrar(CandidatoTo model) throws SQLException, ErroOperacaoException {
		String query = "INSERT INTO T_UNEXT_CANDIDATO (ID_CANDIDATO,ID_PESSOA,ID_ENDERECO,DS_ESCOLARIDADE,DS_ATUACAO) "
					+ "VALUES (SQ_T_UNEXT_CANDIDATO.nextval, ?, ?, ?, ?)";

		PreparedStatement stm = conexao.prepareStatement(query, new String[] { "ID_CANDIDATO" });
		stm.setInt(1, model.getIdPessoa());
		stm.setInt(2, model.getEndereco().getIdEnderaco());
		stm.setString(3, model.getEscolaridade());
		stm.setString(4, model.getAtuacao());

		stm.executeUpdate();

		ResultSet result = stm.getGeneratedKeys();
		if (result.next()) {
			model.setIdCandidato(result.getInt(1));
			return model.getIdCandidato();
		}

		throw new ErroOperacaoException("Não foi possivel realizar o cadastro");
	}

	@Override
	public boolean editar(CandidatoTo model) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remover(int id) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<CandidatoTo> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CandidatoTo buscarById(int id) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
