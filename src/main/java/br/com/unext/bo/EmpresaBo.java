package br.com.unext.bo;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.unext.dao.EmpresaDao;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.to.EmpresaTo;

public class EmpresaBo {
	
	private EmpresaDao dao;
	
	public EmpresaBo(Connection conexao) {
		dao = new EmpresaDao(conexao);
	}
	
	public int cadastrarEmpresa(EmpresaTo empresa) throws SQLException, ErroOperacaoException {
		return dao.cadastrar(empresa);
	}
	
	public int cadastrarEndereco(int idEmpresa, int idEndereco) throws SQLException, ErroOperacaoException {
		return dao.cadastrarEndereco(idEmpresa, idEndereco);
	}

}
