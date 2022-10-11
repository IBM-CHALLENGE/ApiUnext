package br.com.unext.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.NaoEncontradoException;
import br.com.unext.to.UsuarioTo;

public class UsuarioDao implements IDao<UsuarioTo> {

	private Connection conexao;

	public UsuarioDao(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public int cadastrar(UsuarioTo model) throws SQLException, ErroOperacaoException {
		String query = "INSERT INTO T_UNEXT_USUARIO (ID_USUARIO, DS_USER, DS_SENHA) "
				+ "VALUES (SQ_T_UNEXT_USUARIO.NEXTVAL, ?, ?)";

		PreparedStatement stm = conexao.prepareStatement(query, new String[] { "ID_USUARIO" });
		stm.setString(1, model.getLogin());
		stm.setString(2, model.getSenha());

		stm.executeUpdate();

		ResultSet result = stm.getGeneratedKeys();
		if (result.next()) {
			model.setId(result.getInt(1));
			return model.getId();
		}

		throw new ErroOperacaoException("Não foi possivel realizar o cadastro");
	}

	@Override
	public boolean editar(UsuarioTo model) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remover(int id) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<UsuarioTo> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsuarioTo buscarById(int id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public UsuarioTo buscaByEmail(String email) throws SQLException {

		String query = "SELECT * FROM T_UNEXT_USUARIO WHERE DS_USER = ?";

		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setString(1, email);

		ResultSet resultado = stm.executeQuery();

		if (resultado.next()) {
			UsuarioTo user = new UsuarioTo();
			user.setId(resultado.getInt(1));
			user.setLogin(resultado.getString(2));
			user.setSenha(resultado.getString(3));

			return user;
		}

		return null;
	}
	
	public int idUsuario(UsuarioTo usuario) throws NaoEncontradoException, SQLException {
		
		String query = "SELECT id_usuario FROM T_UNEXT_USUARIO WHERE ds_user = ? AND ds_senha = ?";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setString(1, usuario.getLogin());
		stm.setString(2, usuario.getSenha());
		
		ResultSet resultado = stm.executeQuery();

		if (resultado.next())
			return resultado.getInt(1);
		
		throw new NaoEncontradoException("Usuario não encontrado");
	}
}
