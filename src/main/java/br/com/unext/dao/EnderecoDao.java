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
		String query = "UPDATE "
				+ "    T_UNEXT_ENDERECO "
				+ "SET "
				+ "    DS_LOGRADOURO = ?, "
				+ "    NR_NUMERO = ?, "
				+ "    NR_CEP = ?, "
				+ "    NM_CIDADE = ?, "
				+ "    NM_ESTADO = ?, "
				+ "    NM_BAIRRO = ?, "
				+ "    DS_REFERENCIA = ? "
				+ "WHERE "
				+ "    ID_ENDERECO = ?";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setString(1, model.getLogradouro());
		stm.setInt(2, model.getNumero());
		stm.setString(3, model.getCep());
		stm.setString(4, model.getCidade());
		stm.setString(5, model.getUf());
		stm.setString(6, model.getBairro());
		stm.setString(7, model.getComplemento());
		stm.setInt(8, model.getIdEnderaco());
		
		if(stm.executeUpdate() < 1)
			throw new ErroOperacaoException("Nao foi possivel atualizar a pessoa");
		
		return true;
	}

	@Override
	public boolean remover(int id) throws SQLException, ErroOperacaoException {
		String query = "DELETE T_UNEXT_ENDERECO WHERE ID_ENDERECO = ?";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setInt(1, id);
		
		if(stm.executeUpdate() < 1)
			throw new ErroOperacaoException("Nao foi possivel remover o endereco");
		
		return true;
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

	public ArrayList<EnderecoTo> listarByIdEmpresa(int idEmpresa) throws SQLException{
		ArrayList<EnderecoTo> enderecos = new ArrayList<EnderecoTo>();
		
		String query = "SELECT  "
				+ "    T_UNEXT_ENDERECO.ID_ENDERECO ID_ENDERECO, "
				+ "    T_UNEXT_ENDERECO.DS_LOGRADOURO DS_LOGRADOURO, "
				+ "    T_UNEXT_ENDERECO.NR_NUMERO NR_NUMERO, "
				+ "    T_UNEXT_ENDERECO.NR_CEP NR_CEP, "
				+ "    T_UNEXT_ENDERECO.NM_CIDADE NM_CIDADE, "
				+ "    T_UNEXT_ENDERECO.NM_ESTADO NM_ESTADO, "
				+ "    T_UNEXT_ENDERECO.NM_BAIRRO NM_BAIRRO, "
				+ "    T_UNEXT_ENDERECO.DS_REFERENCIA DS_REFERENCIA "
				+ "FROM "
				+ "    T_UNEXT_ENDERECO "
				+ "    INNER JOIN T_UNEXT_ENDERECO_EMPRESA "
				+ "        ON T_UNEXT_ENDERECO.ID_ENDERECO = T_UNEXT_ENDERECO_EMPRESA.ID_ENDERECO "
				+ "WHERE "
				+ "    T_UNEXT_ENDERECO_EMPRESA.ID_EMPRESA = ?";
		
		PreparedStatement stm = conexao.prepareStatement(query, new String[] { "ID_ENDERECO" });
		stm.setInt(1, idEmpresa);
		
		ResultSet result = stm.executeQuery();
		
		while(result.next()) {
			EnderecoTo endereco = new EnderecoTo();
			
			endereco.setIdEnderaco(result.getInt(1));
			endereco.setLogradouro(result.getString(2));
			endereco.setNumero(result.getInt(3));
			endereco.setCep(result.getString(4));
			endereco.setCidade(result.getString(5));
			endereco.setUf(result.getString(6));
			endereco.setBairro(result.getString(7));
			endereco.setComplemento(result.getString(8));
			
			enderecos.add(endereco);
		}
		
		return enderecos;
	}
}
