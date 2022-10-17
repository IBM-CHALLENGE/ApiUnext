package br.com.unext.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.dao.VagaDao;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.NaoEncontradoException;
import br.com.unext.to.EmpresaTo;
import br.com.unext.to.SkillTo;
import br.com.unext.to.VagaTo;

public class VagaBo {

	private VagaDao dao;
	private EmpresaBo empresaBo;
	private CandidaturaBo candidaturaBo;
	
	public VagaBo(Connection conexao) throws ClassNotFoundException, SQLException {
		this.dao = new VagaDao(conexao);
		this.empresaBo = new EmpresaBo(conexao);
		this.candidaturaBo = new CandidaturaBo(conexao);
	}
	
	public int cadastrar(VagaTo vaga) throws SQLException, ErroOperacaoException {
		return dao.cadastrar(vaga);
	}
	
	public ArrayList<VagaTo> listar() throws SQLException, ErroOperacaoException, NaoEncontradoException{
		ArrayList<VagaTo> vagas = dao.listar();
		
		for (VagaTo vaga : vagas) {
			vaga.setEmpresa(empresaBo.buscarById(vaga.getEmpresa().getId()));
		}
		
		return vagas;
	}
	
	public boolean editar(VagaTo vaga) throws SQLException, ErroOperacaoException {
		return dao.editar(vaga);
	}
	
	public boolean remover(int id) throws SQLException, ErroOperacaoException {
		return dao.remover(id);
	}
	
	public VagaTo buscarById(int id) throws SQLException, ErroOperacaoException, NaoEncontradoException {
		VagaTo vaga = dao.buscarById(id);
		vaga.setEmpresa(empresaBo.buscarById(vaga.getEmpresa().getId()));
		vaga.setCandidaturas(candidaturaBo.buscarByIdVaga(id));
		
		return vaga;
	}
	
	public ArrayList<VagaTo> listarByIdEmpresa(int idEmpresa) throws SQLException, ErroOperacaoException, NaoEncontradoException{
		EmpresaTo empresa = empresaBo.buscarById(idEmpresa);
		return dao.listarByIdEmpresa(empresa);
	}
	
	public boolean adicionarSkill(int idVaga, SkillTo skill) throws ErroOperacaoException, SQLException {
		return dao.adicionarSkill(idVaga, skill);
	}
	
	public boolean removerSkill(int idVaga, int idSkill) throws ErroOperacaoException, SQLException {
		return dao.removerSkill(idVaga, idSkill);
	}
}
