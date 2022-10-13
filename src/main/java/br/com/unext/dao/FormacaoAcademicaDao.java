package br.com.unext.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.NaoEncontradoException;
import br.com.unext.to.FormacaoAcademicaTo;

public class FormacaoAcademicaDao implements IDao<FormacaoAcademicaTo>{

	private Connection conexao;

	public FormacaoAcademicaDao(Connection conexao) {
		this.conexao = conexao;
	}
	
	@Override
	public int cadastrar(FormacaoAcademicaTo model) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean editar(FormacaoAcademicaTo model) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remover(int id) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<FormacaoAcademicaTo> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FormacaoAcademicaTo buscarById(int id) throws SQLException, ErroOperacaoException, NaoEncontradoException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<FormacaoAcademicaTo> listarByIdCandidato(int idCandidato) throws SQLException{
		ArrayList<FormacaoAcademicaTo> formacoes = new ArrayList<FormacaoAcademicaTo>();
		
		String query = "SELECT  "
					+ "    ID_GRADUACAO, "
					+ "    DS_NIVEL_GRADUCAO, "
					+ "    DS_GRADUCAO, "
					+ "    DS_INSTITUICAO, "
					+ "    TO_CHAR(DT_INICIO, 'DD/MM/YYYY'), "
					+ "    TO_CHAR(DT_FIM, 'DD/MM/YYYY')   "
					+ "FROM  "
					+ "    T_UNEXT_FORMACAO_ACADEMICA  "
					+ "WHERE  "
					+ "    ID_CANDIDATO = ?";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setInt(1, idCandidato);
		ResultSet result = stm.executeQuery();
		
		while(result.next()) {
			FormacaoAcademicaTo formacao = new FormacaoAcademicaTo();
			
			formacao.setId(result.getInt(1));
			formacao.setGrauAcademico(result.getString(2));
			formacao.setCurso(result.getString(3));
			formacao.setInstituicao(result.getString(4));
			formacao.setDataInicio(result.getString(5));
			formacao.setDataFim(result.getString(6));
			
			formacoes.add(formacao);
		}

		return formacoes;
	}

}
