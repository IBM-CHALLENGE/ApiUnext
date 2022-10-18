package br.com.unext.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.dao.SkillDao;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.to.SkillTo;

public class SkillBo {
	
	private SkillDao dao;
	
	public SkillBo(Connection conexao) {
		this.dao = new SkillDao(conexao);
	}
	
	public int cadastrar(SkillTo skill) throws SQLException, ErroOperacaoException {
		return dao.cadastrar(skill);
	}
	
	public ArrayList<SkillTo> listarByIdCandidato(int idCandidato) throws SQLException{
		return dao.listarByIdCandidato(idCandidato);
	}
	
	public ArrayList<SkillTo> listarHardSkills() throws SQLException{
		return dao.listarHardSkills();
	}
}
