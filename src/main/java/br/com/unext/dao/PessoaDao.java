package br.com.unext.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.exceptions.ErroOperacaoException;
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
	public boolean editar(PessoaTo model) {
		// TODO Auto-generated method stub
		return false;
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

}
