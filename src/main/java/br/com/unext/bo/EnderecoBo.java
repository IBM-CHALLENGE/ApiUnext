package br.com.unext.bo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.unext.dao.EnderecoDao;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.to.EnderecoTo;

public class EnderecoBo {
	
	private EnderecoDao dao;
	
	public EnderecoBo(Connection conexao) throws ClassNotFoundException, SQLException {
		this.dao = new EnderecoDao(conexao);
	}

	public int cadastrarEndereco(EnderecoTo endereco) throws SQLException, ErroOperacaoException {
		return dao.cadastrar(endereco);
	}
	
	public boolean remover(int id) throws SQLException, ErroOperacaoException {
		return dao.remover(id);
	}
	
	public boolean atualizarEndereco(EnderecoTo endereco) throws SQLException, ErroOperacaoException {
		return dao.editar(endereco);
	}
	
	public ArrayList<EnderecoTo> listarByIdEmpresa(int idEmpresa) throws SQLException{
		return dao.listarByIdEmpresa(idEmpresa);
	}
}
