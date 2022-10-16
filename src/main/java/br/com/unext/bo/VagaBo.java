package br.com.unext.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.dao.VagaDao;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.NaoEncontradoException;
import br.com.unext.to.EmpresaTo;
import br.com.unext.to.VagaTo;

public class VagaBo {

	private VagaDao dao;
	private EmpresaBo empresaBo;
	
	public VagaBo(Connection conexao) throws ClassNotFoundException, SQLException {
		this.dao = new VagaDao(conexao);
		this.empresaBo = new EmpresaBo(conexao);
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
	
	public VagaTo buscarById(int id) throws SQLException, ErroOperacaoException, NaoEncontradoException {
		VagaTo vaga = dao.buscarById(id);
		vaga.setEmpresa(empresaBo.buscarById(vaga.getEmpresa().getId()));
		
		return vaga;
	}
	
	public ArrayList<VagaTo> listarByIdEmpresa(int idEmpresa) throws SQLException, ErroOperacaoException, NaoEncontradoException{
		EmpresaTo empresa = empresaBo.buscarById(idEmpresa);
		return dao.listarByIdEmpresa(empresa);
	}
}
