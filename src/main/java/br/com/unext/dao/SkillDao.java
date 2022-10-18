package br.com.unext.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.NaoEncontradoException;
import br.com.unext.to.SkillTo;

public class SkillDao implements IDao<SkillTo>{

	private Connection conexao;

	public SkillDao(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public int cadastrar(SkillTo model) throws SQLException, ErroOperacaoException {
		String query = "INSERT INTO T_UNEXT_SKILL (id_skill, tp_skill, ds_skill) VALUES (sq_t_unext_skill.nextval, ?, ?)";
		
		PreparedStatement stm = conexao.prepareStatement(query, new String[] {"id_skill"});
		stm.setString(1, "S");
		stm.setString(2, model.getDescricao());
		
		stm.executeUpdate();
		
		ResultSet result = stm.getGeneratedKeys();
		if(result.next()) {
			model.setId(result.getInt(1));
			return model.getId();
		}
		
		throw new ErroOperacaoException("Não foi possivel cadastrar a skill");
	}

	@Override
	public boolean editar(SkillTo model) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remover(int id) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<SkillTo> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SkillTo buscarById(int id) throws SQLException, ErroOperacaoException, NaoEncontradoException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<SkillTo> listarByIdCandidato(int idCandidato) throws SQLException{
		ArrayList<SkillTo> skills = new ArrayList<SkillTo>();
		
		String query = "SELECT "
					+ "    T_UNEXT_SKILL.ID_SKILL ID_SKILL, "
					+ "    T_UNEXT_SKILL.DS_SKILL DS_SKILL, "
					+ "    T_UNEXT_SKILL.TP_SKILL TP_SKILL, "
					+ "    T_UNEXT_SKILL_CANDIDATO.DS_NIVEL DS_NIVEL "
					+ "FROM "
					+ "    T_UNEXT_SKILL "
					+ "    INNER JOIN T_UNEXT_SKILL_CANDIDATO "
					+ "        ON T_UNEXT_SKILL.ID_SKILL = T_UNEXT_SKILL_CANDIDATO.ID_SKILL "
					+ "WHERE "
					+ "    T_UNEXT_SKILL_CANDIDATO.ID_CANDIDATO = ? "
					+ "ORDER BY "
					+ "		T_UNEXT_SKILL.TP_SKILL, "
					+ "		T_UNEXT_SKILL_CANDIDATO.DS_NIVEL DESC, "
					+ "		T_UNEXT_SKILL.DS_SKILL";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setInt(1, idCandidato);
		ResultSet result = stm.executeQuery();
		
		while(result.next()) {
			SkillTo skill = new SkillTo();
			
			skill.setId(result.getInt(1));
			skill.setDescricao(result.getString(2));
			skill.setTipo(result.getString(3).charAt(0));
			skill.setNivel(result.getInt(4));
			
			skills.add(skill);
		}

		return skills;
	}
	
	public ArrayList<SkillTo> listarHardSkills () throws SQLException{
		ArrayList<SkillTo> skills = new ArrayList<SkillTo>();
		
		String query = "SELECT * FROM T_UNEXT_SKILL ORDER BY DS_SKILL";
		PreparedStatement stm = conexao.prepareStatement(query);
		ResultSet result = stm.executeQuery();
		
		while(result.next()) {
			SkillTo skill = new SkillTo();
			
			skill.setId(result.getInt(1));
			skill.setDescricao(result.getString(2));
			skill.setTipo(result.getString(3).charAt(0));
			
			skills.add(skill);
		}

		return skills;
	}
}
