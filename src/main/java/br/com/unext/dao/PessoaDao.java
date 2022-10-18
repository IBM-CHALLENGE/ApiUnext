package br.com.unext.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.NaoEncontradoException;
import br.com.unext.to.PessoaTo;

public class PessoaDao implements IDao<PessoaTo> {

	private Connection conexao;

	public PessoaDao(Connection conexao) {
		this.conexao = conexao;
	}
	
	@Override
	public int cadastrar(PessoaTo model) throws SQLException, ErroOperacaoException {
		String query = "INSERT INTO T_UNEXT_PESSOA(ID_PESSOA, ID_USUARIO, NM_NOME, NR_RG, NR_CPF, DT_NACIMENTO, SG_SEXO) "
					+ "VALUES (SQ_T_UNEXT_PESSOA.nextval, ?, ?, ?, ?, TO_DATE( ? ,'DD/MM/YYYY'), ?)";

		PreparedStatement stm = conexao.prepareStatement(query, new String[] { "ID_PESSOA" });
		stm.setInt(1, model.getUsuario().getId());
		stm.setString(2, model.getNome());
		stm.setString(3, model.getRg());
		stm.setString(4, model.getCpf());
		stm.setString(5, model.getDataNascimento());
		stm.setString(6, model.getSexo()+"");

		stm.executeUpdate();

		ResultSet result = stm.getGeneratedKeys();
		if (result.next()) {
			model.setIdPessoa(result.getInt(1));
			return model.getIdPessoa();
		}

		throw new ErroOperacaoException("Não foi possivel realizar o cadastro");
	}

	@Override
	public boolean editar(PessoaTo model) throws SQLException, ErroOperacaoException {
		
		String query = "UPDATE  "
				+ "    T_UNEXT_PESSOA "
				+ "SET "
				+ "    NM_NOME = ?, "
				+ "    NR_RG = ?, "
				+ "    NR_CPF = ?, "
				+ "    DT_NACIMENTO = TO_DATE( ? , 'DD/MM/YYYY'), "
				+ "    SG_SEXO = ? "
				+ "WHERE "
				+ "    ID_PESSOA = ?";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setString(1, model.getNome());
		stm.setString(2, model.getRg());
		stm.setString(3, model.getCpf());
		stm.setString(4, model.getDataNascimento());
		stm.setString(5, model.getSexo()+"");
		stm.setInt(6, model.getIdPessoa());
		
		if(stm.executeUpdate() < 1)
			throw new ErroOperacaoException("Nao foi possivel atualizar a pessoa");
		
		return true;
	}

	@Override
	public boolean remover(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<PessoaTo> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PessoaTo buscarById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public int buscarIdPessoaByIdCandidato(int idCandidato) throws SQLException, NaoEncontradoException {
		
		String query = "SELECT  "
					+ "    T_UNEXT_PESSOA.ID_PESSOA "
					+ "FROM  "
					+ "    T_UNEXT_PESSOA "
					+ "    INNER JOIN T_UNEXT_CANDIDATO  "
					+ "        ON T_UNEXT_PESSOA.ID_PESSOA = T_UNEXT_CANDIDATO.ID_PESSOA "
					+ "WHERE "
					+ "    T_UNEXT_CANDIDATO.ID_CANDIDATO = ?";
		
		
		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setInt(1, idCandidato);
		
		ResultSet result = stm.executeQuery();
		
		if(result.next())
			return result.getInt(1);
		
		throw new NaoEncontradoException("Pessoa não encontrada");
	}
}
