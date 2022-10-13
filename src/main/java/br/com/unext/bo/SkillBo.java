package br.com.unext.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.dao.SkillDao;
import br.com.unext.to.SkillTo;

public class SkillBo {
	
	private SkillDao dao;
	
	public SkillBo(Connection conexao) {
		dao = new SkillDao(conexao);
	}
	
	public ArrayList<SkillTo> listarByIdCandidato(int idCandidato) throws SQLException{
		return dao.listarByIdCandidato(idCandidato);
	}
}
