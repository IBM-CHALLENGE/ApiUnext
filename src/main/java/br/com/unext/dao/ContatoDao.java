package br.com.unext.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.NaoEncontradoException;
import br.com.unext.to.ContatoTo;

public class ContatoDao implements IDao<ContatoTo> {

	private Connection conexao;

	public ContatoDao(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public int cadastrar(ContatoTo model) throws SQLException, ErroOperacaoException {
		String query = "INSERT INTO T_UNEXT_CONTATO (ID_CONTATO,ID_PESSOA,DS_EMAIL,NR_TELEFONE) "
					+ "VALUES (SQ_T_UNEXT_CONTATO.nextval, ?, ?, ?)";

		PreparedStatement stm = conexao.prepareStatement(query, new String[] { "ID_CONTATO" });
		stm.setInt(1, model.getId());
		stm.setString(2, model.getEmail());
		stm.setString(3, model.getTelefone());

		stm.executeUpdate();

		ResultSet result = stm.getGeneratedKeys();
		if (result.next()) {
			model.setId(result.getInt(1));
			return model.getId();
		}

		throw new ErroOperacaoException("Não foi possivel realizar o cadastro");
	}

	@Override
	public boolean editar(ContatoTo model) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remover(int id) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<ContatoTo> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContatoTo buscarById(int id) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<ContatoTo> listarByIdPessoa(int idPessoa) throws SQLException, ErroOperacaoException, NaoEncontradoException {
		
		ArrayList<ContatoTo> contatos = new ArrayList<ContatoTo>();
		String query = "SELECT ID_CONTATO, DS_EMAIL, NR_TELEFONE FROM T_UNEXT_CONTATO WHERE ID_PESSOA = ?";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setInt(1, idPessoa);
		ResultSet result = stm.executeQuery();
		
		while (result.next()) {
			ContatoTo contato = new ContatoTo();
			
			contato.setId(result.getInt(1));
			contato.setEmail(result.getString(2));
			contato.setTelefone(result.getString(3));
			
			contatos.add(contato);
		}
		
		return contatos;
	}

}
