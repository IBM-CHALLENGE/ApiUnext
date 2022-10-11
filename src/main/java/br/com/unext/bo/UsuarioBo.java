package br.com.unext.bo;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.unext.dao.UsuarioDao;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.JaExistenteException;
import br.com.unext.to.CandidatoTo;
import br.com.unext.to.ContatoTo;
import br.com.unext.to.EmpresaTo;

public class UsuarioBo {

	private UsuarioDao dao;
	private PessoaBo pessoaBo;
	private EnderecoBo enderecoBo;
	private ContatoBo contatoBo;
	private CandidatoBo candidatoBo;
	private EmpresaBo empresaBo;

	public UsuarioBo(Connection conexao) throws ClassNotFoundException, SQLException {
		dao = new UsuarioDao(conexao);
		pessoaBo = new PessoaBo(conexao);
		enderecoBo = new EnderecoBo(conexao);
		contatoBo = new ContatoBo(conexao);
		candidatoBo = new CandidatoBo(conexao);
		empresaBo = new EmpresaBo(conexao);
	}

	public void cadastrarUsuarioCandidato(CandidatoTo candidato) throws SQLException, JaExistenteException, ErroOperacaoException {

		if (dao.buscaByEmail(candidato.getUsuario().getLogin()) != null)
			throw new JaExistenteException("Email já cadastrado");
		
		dao.cadastrar(candidato.getUsuario());
		enderecoBo.cadastrarEndereco(candidato.getEndereco());
		pessoaBo.cadastrarPessoa(candidato);
		
		ContatoTo contato = new ContatoTo();
		contato.setIdPessoa(candidato.getIdPessoa());
		contato.setEmail(candidato.getUsuario().getLogin());
		contatoBo.cadastrarContato(contato);
		
		candidatoBo.cadastrarCandidato(candidato);
	}

	public void cadastrarUsuarioEmpresa(EmpresaTo empresa) throws SQLException, JaExistenteException, ErroOperacaoException {

		if (dao.buscaByEmail(empresa.getUsuario().getLogin()) != null)
			throw new JaExistenteException("Email já cadastrado");

		dao.cadastrar(empresa.getUsuario());
		empresaBo.cadastrarEmpresa(empresa);
		
		int idEndereco = enderecoBo.cadastrarEndereco(empresa.getEnderecos().get(0));
		empresaBo.cadastrarEndereco(empresa.getId(), idEndereco);
		
	}
}
