package br.com.unext.bo;

import java.sql.SQLException;

import br.com.unext.dao.UsuarioDao;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.JaExistenteException;
import br.com.unext.factory.ConnectionFactory;
import br.com.unext.to.CandidatoTo;
import br.com.unext.to.ContatoTo;

public class UsuarioBo {

	private UsuarioDao dao;
	private PessoaBo pessoaBo;
	private EnderecoBo enderecoBo;
	private ContatoBo contatoBo;
	private CandidatoBo candidatoBo;

	public UsuarioBo() throws ClassNotFoundException, SQLException {
		dao = new UsuarioDao(ConnectionFactory.getConnection());
		pessoaBo = new PessoaBo();
		enderecoBo = new EnderecoBo();
		contatoBo = new ContatoBo();
		candidatoBo = new CandidatoBo();
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

//	public int cadastrarUsuarioEmpresa(EmpresaTo empresa) throws SQLException, UsuarioJaExistenteException, ErroOperacaoException {
//
//		if (dao.buscaByEmail(user.getLogin()) != null)
//			throw new UsuarioJaExistenteException("Email já cadastrado");
//
//		return dao.cadastrar(user);
//	}
}
