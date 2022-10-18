package br.com.unext.bo;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.unext.dao.CandidatoDao;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.NaoEncontradoException;
import br.com.unext.to.CandidatoTo;
import br.com.unext.to.ContatoTo;
import br.com.unext.to.FormacaoAcademicaTo;
import br.com.unext.to.SkillTo;

public class CandidatoBo {
	
	private CandidatoDao dao;
	private ContatoBo contatoBo;
	private FormacaoAcademicaBo formacaoBo;
	private SkillBo skillBo;
	private PessoaBo pessoaBo;
	private EnderecoBo enderecoBo;
	private UsuarioBo usuarioBo;
	
	public CandidatoBo(Connection conexao) throws ClassNotFoundException, SQLException {
		this.dao = new CandidatoDao(conexao);
		this.contatoBo = new ContatoBo(conexao);
		this.formacaoBo = new FormacaoAcademicaBo(conexao);
		this.skillBo = new SkillBo(conexao);
		this.pessoaBo = new PessoaBo(conexao);
		this.enderecoBo = new EnderecoBo(conexao);
		this.usuarioBo = new UsuarioBo(conexao, this);
	}
	
	public int cadastrarCandidato(CandidatoTo candidato) throws SQLException, ErroOperacaoException {
		return dao.cadastrar(candidato);
	}

	public CandidatoTo buscarById(int id) throws SQLException, ErroOperacaoException, NaoEncontradoException {
		CandidatoTo candidato = dao.buscarById(id);
		candidato.setContatos(contatoBo.listarByIdPessoa(candidato.getIdPessoa()));
		candidato.setFormacoesAcademicas(formacaoBo.listarByIdCandidato(id));
		candidato.setSkills(skillBo.listarByIdCandidato(id));
		
		return candidato;
	}
	
	public void mudarFoto(CandidatoTo candidato) throws SQLException, NaoEncontradoException, ErroOperacaoException {
		int idPessoa = pessoaBo.buscarIdPessoaByIdCandidato(candidato.getIdCandidato());
		candidato.setIdPessoa(idPessoa);
		dao.mudarFoto(candidato);
	}
	
	public void adicionarContato(int idCandidato, ContatoTo contato) throws SQLException, NaoEncontradoException, ErroOperacaoException {
		int idPessoa = pessoaBo.buscarIdPessoaByIdCandidato(idCandidato);
		contato.setId(idPessoa);
		contatoBo.cadastrarContato(contato);
	}
	
	public boolean removerContato(int idContato) throws SQLException, ErroOperacaoException {
		return contatoBo.removerContato(idContato);
	}
	
	public int adicionarFormacao(int idCandidato, FormacaoAcademicaTo formacao) throws SQLException, ErroOperacaoException {
		formacao.setId(idCandidato);
		return formacaoBo.cadastrar(formacao);
	}
	
	public boolean removerFormacao(int id) throws SQLException, ErroOperacaoException {
		return formacaoBo.remover(id);
	}
	
	public int adicionarSkill(int idCandidato, SkillTo skill) throws SQLException, ErroOperacaoException {
		
		if(skill.getTipo() == 'S') {
			skillBo.cadastrar(skill);
		}
		
		int idSkillCandidato = dao.cadastrarSkillCandidato(idCandidato, skill);
		
		return idSkillCandidato;
	}
	
	public boolean removerSkillCandidato(int idSkill) throws SQLException, ErroOperacaoException {
		return dao.removerSkillCandidato(idSkill);
	}

	public boolean atualizarCandidato(CandidatoTo candidato) throws SQLException, ErroOperacaoException {
		boolean atualizou = true;
		
		atualizou = atualizou == true && dao.editar(candidato);
		atualizou = atualizou == true && pessoaBo.atualizarPessoa(candidato);
		atualizou = atualizou == true && enderecoBo.atualizarEndereco(candidato.getEndereco());
		atualizou = atualizou == true && usuarioBo.atualizarUsuario(candidato.getUsuario());
		
		return atualizou;
	}
}
