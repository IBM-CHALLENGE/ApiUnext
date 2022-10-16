package br.com.unext.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.NaoEncontradoException;
import br.com.unext.to.EmpresaTo;
import br.com.unext.to.SkillTo;
import br.com.unext.to.VagaTo;

public class VagaDao implements IDao<VagaTo>{
	private Connection conexao;

	public VagaDao(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public int cadastrar(VagaTo model) throws SQLException, ErroOperacaoException {
		String query = "INSERT INTO T_UNEXT_VAGA (ID_VAGA,ID_EMPRESA,DS_DESCRICAO,DT_CADASTRO,DS_CARGO,VL_SALARIO,DT_ENCERRAMENTO,QT_VAGA_DISPONIVEL) "
					+ "VALUES (sq_t_unext_vaga.nextval, ?, ?, SYSDATE, ?, ?, TO_DATE(?, 'DD/MM/YYYY'), ?)";
		
		PreparedStatement stm = conexao.prepareStatement(query, new String[] {"ID_VAGA"});
		
		stm.setInt(1, model.getEmpresa().getId());
		stm.setString(2, model.getDescricao());
		stm.setString(3, model.getCargo());
		stm.setDouble(4, model.getSalario());
		stm.setString(5, model.getDataEncerramento());
		stm.setInt(6, model.getQtdVagas());
		
		stm.executeUpdate();
		
		ResultSet result = stm.getGeneratedKeys();
		if (result.next()) {
			model.setId(result.getInt(1));
			return model.getId();
		}
		
		throw new ErroOperacaoException("Nao foi possivel cadastrar a vaga");
	}

	@Override
	public boolean editar(VagaTo model) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remover(int id) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<VagaTo> listar() throws SQLException {
		ArrayList<VagaTo> vagas = new ArrayList<VagaTo>();
		
		String query = "SELECT "
					+ "    T_UNEXT_VAGA.ID_VAGA ID_VAGA, "
					+ "    T_UNEXT_VAGA.DS_CARGO DS_CARGO, "
					+ "    T_UNEXT_VAGA.QT_VAGA_DISPONIVEL QT_VAGA_DISPONIVEL, "
					+ "    T_UNEXT_VAGA.VL_SALARIO VL_SALARIO, "
					+ "    TO_CHAR(T_UNEXT_VAGA.DT_ENCERRAMENTO, 'DD/MM/YYYY') DT_ENCERRAMENTO, "
					+ "    T_UNEXT_VAGA.DS_DESCRICAO DS_DESCRICAO, "
					+ "    TO_CHAR(T_UNEXT_VAGA.DT_CADASTRO, 'DD/MM/YYYY') DT_CADASTRO, "
					+ "    T_UNEXT_VAGA.ST_STATUS ST_STATUS_VAGA, "
					+ ""
					+ "	   T_UNEXT_EMPRESA.ID_EMPRESA ID_EMPRESA "
					+ "FROM  "
					+ "    T_UNEXT_VAGA "
					+ "    INNER JOIN T_UNEXT_EMPRESA "
					+ "        ON T_UNEXT_VAGA.ID_EMPRESA = T_UNEXT_EMPRESA.ID_EMPRESA "
					+ "WHERE "
					+ "    T_UNEXT_VAGA.ST_STATUS = 'A'  "
					+ "    AND T_UNEXT_EMPRESA.ST_STATUS = 'A' "
					+ "ORDER BY "
					+ "   	T_UNEXT_VAGA.DT_ENCERRAMENTO, "
					+ "    T_UNEXT_VAGA.DT_CADASTRO DESC";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		
		ResultSet result = stm.executeQuery();
		while(result.next()) {
			VagaTo vaga = new VagaTo();
			EmpresaTo empresa = new EmpresaTo();
			
			vaga.setEmpresa(empresa);
			
			vaga.setId(result.getInt("ID_VAGA"));
			vaga.setCargo(result.getString("DS_CARGO"));
			vaga.setQtdVagas(result.getInt("QT_VAGA_DISPONIVEL"));
			vaga.setSalario(result.getDouble("VL_SALARIO"));
			vaga.setDataEncerramento(result.getString("DT_ENCERRAMENTO"));
			vaga.setDescricao(result.getString("DS_DESCRICAO"));
			vaga.setDataCadastrado(result.getString("DT_CADASTRO"));
			vaga.setStatus(result.getString("ST_STATUS_VAGA").charAt(0));
			
			vaga.getEmpresa().setId(result.getInt("ID_EMPRESA"));
			vaga.setSkillsDesejadas(listarSkillsVaga(vaga.getId()));
			
			vagas.add(vaga);
		}
		
		return vagas;
	}

	@Override
	public VagaTo buscarById(int id) throws SQLException, ErroOperacaoException, NaoEncontradoException {

		String query = "SELECT "
				+ "    T_UNEXT_VAGA.ID_VAGA ID_VAGA, "
				+ "    T_UNEXT_VAGA.DS_CARGO DS_CARGO, "
				+ "    T_UNEXT_VAGA.QT_VAGA_DISPONIVEL QT_VAGA_DISPONIVEL, "
				+ "    T_UNEXT_VAGA.VL_SALARIO VL_SALARIO, "
				+ "    TO_CHAR(T_UNEXT_VAGA.DT_ENCERRAMENTO, 'DD/MM/YYYY') DT_ENCERRAMENTO, "
				+ "    T_UNEXT_VAGA.DS_DESCRICAO DS_DESCRICAO, "
				+ "    TO_CHAR(T_UNEXT_VAGA.DT_CADASTRO, 'DD/MM/YYYY') DT_CADASTRO, "
				+ "    T_UNEXT_VAGA.ST_STATUS ST_STATUS_VAGA, "
				+ ""
				+ "	   T_UNEXT_EMPRESA.ID_EMPRESA ID_EMPRESA "
				+ "FROM  "
				+ "    T_UNEXT_VAGA "
				+ "    INNER JOIN T_UNEXT_EMPRESA "
				+ "        ON T_UNEXT_VAGA.ID_EMPRESA = T_UNEXT_EMPRESA.ID_EMPRESA "
				+ "WHERE "
				+ "    T_UNEXT_VAGA.ST_STATUS = 'A'  "
				+ "    AND T_UNEXT_EMPRESA.ST_STATUS = 'A' "
				+ "	   AND T_UNEXT_VAGA.ID_VAGA = ?"
				+ "ORDER BY "
				+ "   	T_UNEXT_VAGA.DT_ENCERRAMENTO, "
				+ "    T_UNEXT_VAGA.DT_CADASTRO DESC";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setInt(1, id);
		
		ResultSet result = stm.executeQuery();
		if(result.next()) {
			VagaTo vaga = new VagaTo();
			EmpresaTo empresa = new EmpresaTo();
			
			vaga.setEmpresa(empresa);
			
			vaga.setId(result.getInt("ID_VAGA"));
			vaga.setCargo(result.getString("DS_CARGO"));
			vaga.setQtdVagas(result.getInt("QT_VAGA_DISPONIVEL"));
			vaga.setSalario(result.getDouble("VL_SALARIO"));
			vaga.setDataEncerramento(result.getString("DT_ENCERRAMENTO"));
			vaga.setDescricao(result.getString("DS_DESCRICAO"));
			vaga.setDataCadastrado(result.getString("DT_CADASTRO"));
			vaga.setStatus(result.getString("ST_STATUS_VAGA").charAt(0));
			
			vaga.getEmpresa().setId(result.getInt("ID_EMPRESA"));
			vaga.setSkillsDesejadas(listarSkillsVaga(vaga.getId()));
			
			return vaga;
		}
		
		throw new NaoEncontradoException("Vaga não encontrada");
	}
	
	public ArrayList<VagaTo> listarByIdEmpresa(EmpresaTo empresa) throws SQLException{
		ArrayList<VagaTo> vagas = new ArrayList<VagaTo>();
		
		String query = "SELECT "
					+ "    T_UNEXT_VAGA.ID_VAGA ID_VAGA, "
					+ "    T_UNEXT_VAGA.DS_CARGO DS_CARGO, "
					+ "    T_UNEXT_VAGA.QT_VAGA_DISPONIVEL QT_VAGA_DISPONIVEL, "
					+ "    T_UNEXT_VAGA.VL_SALARIO VL_SALARIO, "
					+ "    TO_CHAR(T_UNEXT_VAGA.DT_ENCERRAMENTO, 'DD/MM/YYYY') DT_ENCERRAMENTO, "
					+ "    T_UNEXT_VAGA.DS_DESCRICAO DS_DESCRICAO, "
					+ "    TO_CHAR(T_UNEXT_VAGA.DT_CADASTRO, 'DD/MM/YYYY') DT_CADASTRO, "
					+ "    T_UNEXT_VAGA.ST_STATUS ST_STATUS_VAGA "
					+ "FROM  "
					+ "    T_UNEXT_VAGA "
					+ "    INNER JOIN T_UNEXT_EMPRESA "
					+ "        ON T_UNEXT_VAGA.ID_EMPRESA = T_UNEXT_EMPRESA.ID_EMPRESA "
					+ "WHERE "
					+ "    T_UNEXT_VAGA.ST_STATUS = 'A'  "
					+ "    AND T_UNEXT_EMPRESA.ST_STATUS = 'A' "
					+ "    AND T_UNEXT_VAGA.ID_EMPRESA = ?"
					+ "ORDER BY "
					+ "    T_UNEXT_VAGA.DT_CADASTRO DESC, "
					+ "    T_UNEXT_VAGA.DT_ENCERRAMENTO";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setInt(1, empresa.getId());
		
		ResultSet result = stm.executeQuery();
		while(result.next()) {
			VagaTo vaga = new VagaTo();
			
			vaga.setId(result.getInt("ID_VAGA"));
			vaga.setCargo(result.getString("DS_CARGO"));
			vaga.setQtdVagas(result.getInt("QT_VAGA_DISPONIVEL"));
			vaga.setSalario(result.getDouble("VL_SALARIO"));
			vaga.setDataEncerramento(result.getString("DT_ENCERRAMENTO"));
			vaga.setDescricao(result.getString("DS_DESCRICAO"));
			vaga.setDataCadastrado(result.getString("DT_CADASTRO"));
			vaga.setStatus(result.getString("ST_STATUS_VAGA").charAt(0));
			
			vaga.setEmpresa(empresa);
			vaga.setSkillsDesejadas(listarSkillsVaga(vaga.getId()));
			
			vagas.add(vaga);
		}
		
		return vagas;
	}
	
	public ArrayList<SkillTo> listarSkillsVaga(int idVaga) throws SQLException{
		ArrayList<SkillTo> skills = new ArrayList<SkillTo>();
		
		String query = "SELECT  "
					+ "T_UNEXT_SKILL.ID_SKILL ID_SKILL, "
					+ "    T_UNEXT_SKILL.DS_SKILL DS_SKILL, "
					+ "    T_UNEXT_SKILL.TP_SKILL TP_SKILL,  "
					+ "    T_UNEXT_SKILL_VAGA.DS_NIVEL DS_NIVEL "
					+ "FROM  "
					+ "    T_UNEXT_SKILL_VAGA  "
					+ "    INNER JOIN T_UNEXT_SKILL "
					+ "        ON T_UNEXT_SKILL_VAGA.ID_SKILL = T_UNEXT_SKILL.ID_SKILL "
					+ "WHERE  "
					+ "    ID_VAGA = ?";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setInt(1, idVaga);
		
		ResultSet result = stm.executeQuery();
		while(result.next()) {
			SkillTo skill = new SkillTo();
			
			skill.setId(result.getInt(1));
			skill.setDescricao(result.getString(2));
			skill.setTipo('H');
			skill.setNivel(result.getInt(4));
			
			skills.add(skill);
		}
		
		return skills;
	}
}
