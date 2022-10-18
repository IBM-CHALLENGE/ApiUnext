package br.com.unext.bo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;

import br.com.unext.dao.EmpresaDao;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.NaoEncontradoException;
import br.com.unext.to.EmpresaTo;
import br.com.unext.to.EnderecoTo;

public class EmpresaBo {
	
	private EmpresaDao dao;
	private EnderecoBo enderecoBo;
	private UsuarioBo usuarioBo;
	
	public EmpresaBo(Connection conexao) throws ClassNotFoundException, SQLException {
		this.dao = new EmpresaDao(conexao);
		this.enderecoBo = new EnderecoBo(conexao);
		this.usuarioBo = new UsuarioBo(conexao, this);
	}
	
	public int cadastrarEmpresa(EmpresaTo empresa) throws SQLException, ErroOperacaoException {
		return dao.cadastrar(empresa);
	}
	
	public int cadastrarEndereco(int idEmpresa, int idEndereco) throws SQLException, ErroOperacaoException {
		return dao.cadastrarEndereco(idEmpresa, idEndereco);
	}
	
	public boolean editar(EmpresaTo empresa) throws SQLException, ErroOperacaoException {
		usuarioBo.atualizarUsuario(empresa.getUsuario());
		return dao.editar(empresa);
	}

	public EmpresaTo buscarById(int id) throws SQLException, ErroOperacaoException, NaoEncontradoException {
		EmpresaTo empresa = dao.buscarById(id);
		empresa.setEnderecos(enderecoBo.listarByIdEmpresa(id));
		
		return empresa;
	}
	
	public void mudarFoto(EmpresaTo empresa) throws SerialException, SQLException, ErroOperacaoException {
		dao.mudarFoto(empresa);
	}
	
	public void adicionarEndereco(int idEmpresa, EnderecoTo endereco) throws SQLException, ErroOperacaoException {
		enderecoBo.cadastrarEndereco(endereco);
		dao.cadastrarEndereco(idEmpresa, endereco.getIdEnderaco());
	}
	
	public void removerEndereco(int idEndereco) throws SQLException, ErroOperacaoException {
		dao.removerEndereco(idEndereco);
		enderecoBo.remover(idEndereco);
	}
}
