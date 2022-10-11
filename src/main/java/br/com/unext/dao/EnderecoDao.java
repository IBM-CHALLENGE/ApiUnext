package br.com.unext.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.to.EnderecoTo;

public class EnderecoDao implements IDao<EnderecoTo> {

	private Connection conexao;

	public EnderecoDao(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public int cadastrar(EnderecoTo model) throws SQLException, ErroOperacaoException {
		String query = "INSERT INTO T_UNEXT_ENDERECO(ID_ENDERECO, DS_LOGRADOURO, NR_NUMERO, NR_CEP, NM_CIDADE, NM_ESTADO, NM_BAIRRO, DS_REFERENCIA) "
					+ "VALUES (SQ_T_UNEXT_ENDERECO.nextval, ?, ?, ?, ?, ?, ?, ?) ";

		PreparedStatement stm = conexao.prepareStatement(query, new String[] { "ID_ENDERECO" });
		stm.setString(1, model.getLogradouro());
		stm.setInt(2, model.getNumero());
		stm.setString(3, model.getCep());
		stm.setString(4, model.getCidade());
		stm.setString(5, model.getUf());
		stm.setString(6, model.getBairro());
		stm.setString(7, model.getComplemento());


		stm.executeUpdate();

		ResultSet result = stm.getGeneratedKeys();
		if (result.next()) {
			model.setIdEnderaco(result.getInt(1));
			return model.getIdEnderaco();
		}

		throw new ErroOperacaoException("Não foi possivel realizar o cadastro");
	}

	@Override
	public boolean editar(EnderecoTo model) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remover(int id) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<EnderecoTo> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EnderecoTo buscarById(int id) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return null;
	}

}
