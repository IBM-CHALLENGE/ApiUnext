package br.com.unext.bo;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.unext.dao.CandidatoDao;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.NaoEncontradoException;
import br.com.unext.to.CandidatoTo;

public class CandidatoBo {
	
	private CandidatoDao dao;
	private ContatoBo contatoBo;
	private FormacaoAcademicaBo formacaoBo;
	private SkillBo skillBo;
	private PessoaBo pessoaBo;
	
	public CandidatoBo(Connection conexao) throws ClassNotFoundException, SQLException {
		dao = new CandidatoDao(conexao);
		contatoBo = new ContatoBo(conexao);
		formacaoBo = new FormacaoAcademicaBo(conexao);
		skillBo = new SkillBo(conexao);
		pessoaBo = new PessoaBo(conexao);
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
	
	public void mudarFoto(String foto, int idCandidato) throws SQLException, NaoEncontradoException, ErroOperacaoException {
		int idPessoa = pessoaBo.buscarIdPessoaByIdCandidato(idCandidato);
		dao.mudarFoto(foto, idPessoa);
	}
}
