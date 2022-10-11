package br.com.unext.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.to.EmpresaTo;

public class EmpresaDao implements IDao<EmpresaTo> {

	private Connection conexao;

	public EmpresaDao(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public int cadastrar(EmpresaTo model) throws SQLException, ErroOperacaoException {
		String query = "INSERT INTO T_UNEXT_EMPRESA (ID_EMPRESA, ID_USUARIO, NM_EMPRESA, NM_RAZAO_SOCIAL, NR_CNPJ) "
				+ "VALUES (SQ_T_UNEXT_EMPRESA.nextval, ?, ?, ?, ?)";

		PreparedStatement stm = conexao.prepareStatement(query, new String[] { "ID_EMPRESA" });
		stm.setInt(1, model.getUsuario().getId());
		stm.setString(2, model.getNome());
		stm.setString(3, model.getRazaoSocial());
		stm.setString(4, model.getCnpj());

		stm.executeUpdate();

		ResultSet result = stm.getGeneratedKeys();
		if (result.next()) {
			model.setId(result.getInt(1));
			return model.getId();
		}

		throw new ErroOperacaoException("Não foi possivel realizar o cadastro");
	}

	@Override
	public boolean editar(EmpresaTo model) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remover(int id) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<EmpresaTo> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpresaTo buscarById(int id) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int cadastrarEndereco(int idEmpresa, int idEndereco) throws SQLException, ErroOperacaoException {
		String query = "INSERT INTO T_UNEXT_ENDERECO_EMPRESA (ID_ENDERECO_EMPRESA, ID_EMPRESA, ID_ENDERECO) "
				+ "VALUES (SQ_T_UNEXT_ENDERECO_EMPRESA.nextval, ?, ?)";

		PreparedStatement stm = conexao.prepareStatement(query, new String[] { "ID_ENDERECO_EMPRESA" });
		stm.setInt(1, idEmpresa);
		stm.setInt(2, idEndereco);
		stm.executeUpdate();

		ResultSet result = stm.getGeneratedKeys();
		if (result.next()) {
			return result.getInt(1);
		}

		throw new ErroOperacaoException("Não foi possivel realizar o cadastro");
	}

}
