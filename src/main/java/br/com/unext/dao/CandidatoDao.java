package br.com.unext.dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import javax.sql.rowset.serial.SerialException;

import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.NaoEncontradoException;
import br.com.unext.to.CandidatoTo;
import br.com.unext.to.EnderecoTo;
import br.com.unext.to.SkillTo;
import br.com.unext.to.UsuarioTo;

public class CandidatoDao implements IDao<CandidatoTo> {

	private Connection conexao;

	public CandidatoDao(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public int cadastrar(CandidatoTo model) throws SQLException, ErroOperacaoException {
		String query = "INSERT INTO T_UNEXT_CANDIDATO (ID_CANDIDATO,ID_PESSOA,ID_ENDERECO,DS_ESCOLARIDADE,DS_ATUACAO) "
					+ "VALUES (SQ_T_UNEXT_CANDIDATO.nextval, ?, ?, ?, ?)";

		PreparedStatement stm = conexao.prepareStatement(query, new String[] { "ID_CANDIDATO" });
		stm.setInt(1, model.getIdPessoa());
		stm.setInt(2, model.getEndereco().getIdEnderaco());
		stm.setString(3, model.getEscolaridade());
		stm.setString(4, model.getAtuacao());

		stm.executeUpdate();

		ResultSet result = stm.getGeneratedKeys();
		if (result.next()) {
			model.setIdCandidato(result.getInt(1));
			return model.getIdCandidato();
		}

		throw new ErroOperacaoException("Não foi possivel realizar o cadastro");
	}

	@Override
	public boolean editar(CandidatoTo model) throws SQLException, ErroOperacaoException {
		
		String query = "UPDATE  "
				+ "    T_UNEXT_CANDIDATO "
				+ "SET "
				+ "    DS_ESCOLARIDADE = ?, "
				+ "    DS_ATUACAO = ? "
				+ "WHERE "
				+ "    ID_CANDIDATO = ?";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setString(1, model.getEscolaridade());
		stm.setString(2, model.getAtuacao());
		stm.setInt(3, model.getIdCandidato());
		
		if(stm.executeUpdate() < 1)
			throw new ErroOperacaoException("Nao foi possivel atualizar o candidato");
		
		return true;
	}

	@Override
	public boolean remover(int id) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<CandidatoTo> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public CandidatoTo buscarById(int id) throws SQLException, ErroOperacaoException, NaoEncontradoException {
		
		String query = "SELECT "
				+ "    T_UNEXT_CANDIDATO.ID_CANDIDATO ID_CANDIDATO, "
				+ "    T_UNEXT_CANDIDATO.DS_ESCOLARIDADE DS_ESCOLARIDADE, "
				+ "    T_UNEXT_CANDIDATO.DS_ATUACAO DS_ATUACAO, "
				+ "    T_UNEXT_CANDIDATO.ST_STATUS ST_STATUS, "
				
					+ "    T_UNEXT_PESSOA.ID_PESSOA ID_PESSOA, "
					+ "    T_UNEXT_PESSOA.NM_NOME NM_NOME, "
					+ "    T_UNEXT_PESSOA.FT_PERFIL FT_PERFIL, "
					+ "    T_UNEXT_PESSOA.NR_RG NR_RG, "
					+ "    T_UNEXT_PESSOA.NR_CPF NR_CPF, "
					+ "    TO_CHAR(T_UNEXT_PESSOA.DT_NACIMENTO, 'DD/MM/YYYY') DT_NACIMENTO, "
					+ "    T_UNEXT_PESSOA.SG_SEXO SG_SEXO, "
					
					+ "    T_UNEXT_ENDERECO.ID_ENDERECO ID_ENDERECO, "
					+ "    T_UNEXT_ENDERECO.DS_LOGRADOURO DS_LOGRADOURO, "
					+ "    T_UNEXT_ENDERECO.NR_NUMERO NR_NUMERO, "
					+ "    T_UNEXT_ENDERECO.NR_CEP NR_CEP, "
					+ "    T_UNEXT_ENDERECO.NM_CIDADE NM_CIDADE, "
					+ "    T_UNEXT_ENDERECO.NM_ESTADO NM_ESTADO, "
					+ "    T_UNEXT_ENDERECO.NM_BAIRRO NM_BAIRRO, "
					+ "    T_UNEXT_ENDERECO.DS_REFERENCIA DS_REFERENCIA, "
					
					+ "    T_UNEXT_USUARIO.ID_USUARIO ID_USUARIO, "
					+ "    T_UNEXT_USUARIO.DS_USER DS_USER, "
					+ "    T_UNEXT_USUARIO.DS_SENHA DS_SENHA "
					+ "FROM "
					+ "    T_UNEXT_CANDIDATO "
					+ "    INNER JOIN T_UNEXT_PESSOA   "
					+ "        ON T_UNEXT_CANDIDATO.ID_PESSOA = T_UNEXT_PESSOA.ID_PESSOA "
					+ "    INNER JOIN T_UNEXT_ENDERECO "
					+ "        ON T_UNEXT_CANDIDATO.ID_ENDERECO = T_UNEXT_ENDERECO.ID_ENDERECO "
					+ "    INNER JOIN T_UNEXT_USUARIO "
					+ "        ON T_UNEXT_PESSOA.ID_USUARIO = T_UNEXT_USUARIO.ID_USUARIO "
					+ "WHERE "
					+ "    T_UNEXT_CANDIDATO.ST_STATUS = 'A' "
					+ "    AND T_UNEXT_CANDIDATO.ID_CANDIDATO = ?";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setInt(1, id);
		
		ResultSet resultado = stm.executeQuery();
		
		if(resultado.next()) {
			CandidatoTo candidato = new CandidatoTo();
			candidato.setEndereco(new EnderecoTo());
			candidato.setUsuario(new UsuarioTo());
			
			candidato.setIdCandidato(resultado.getInt("ID_CANDIDATO"));
			candidato.setEscolaridade(resultado.getString("DS_ESCOLARIDADE"));
			candidato.setAtuacao(resultado.getString("DS_ATUACAO"));
			candidato.setStatus(resultado.getString("ST_STATUS").charAt(0));
			candidato.setIdPessoa(resultado.getInt("ID_PESSOA"));
			candidato.setNome(resultado.getString("NM_NOME"));
			candidato.setRg(resultado.getString("NR_RG"));
			candidato.setCpf(resultado.getString("NR_CPF"));
			candidato.setDataNascimento(resultado.getString("DT_NACIMENTO"));
			candidato.setSexo(resultado.getString("SG_SEXO").charAt(0));
			
			Blob blob = resultado.getBlob("FT_PERFIL");
			if(blob != null) {
				//convertendo o blob em bytes
				int blobLength = (int) blob.length();  
				byte[] blobAsBytes = blob.getBytes(1, blobLength);
				blob.free();
				
				//bytes para base64
				String base64 = Base64.getEncoder().encodeToString(blobAsBytes);
				
				candidato.setUrlFoto(base64);				
			}
			
			candidato.getEndereco().setIdEnderaco(resultado.getInt("ID_ENDERECO"));
			candidato.getEndereco().setLogradouro(resultado.getString("DS_LOGRADOURO"));
			candidato.getEndereco().setNumero(resultado.getInt("NR_NUMERO"));
			candidato.getEndereco().setCep(resultado.getString("NR_CEP"));
			candidato.getEndereco().setCidade(resultado.getString("NM_CIDADE"));
			candidato.getEndereco().setUf(resultado.getString("NM_ESTADO"));
			candidato.getEndereco().setBairro(resultado.getString("NM_BAIRRO"));
			candidato.getEndereco().setComplemento(resultado.getString("DS_REFERENCIA"));
			
			candidato.getUsuario().setId(resultado.getInt("ID_USUARIO"));
			candidato.getUsuario().setLogin(resultado.getString("DS_USER"));
			candidato.getUsuario().setSenha(resultado.getString("DS_SENHA"));
			
			return candidato;
		}
		
		throw new NaoEncontradoException("Não foi possivel encontrar o candidato");
	}
	
	public int cadastrarSkillCandidato(int idCandidato, SkillTo skill) throws ErroOperacaoException, SQLException {
		String query = "INSERT INTO T_UNEXT_SKILL_CANDIDATO (ID_SKILL_CAND, ID_CANDIDATO, ID_SKILL, DS_NIVEL) "
					+ "VALUES (sq_t_unext_skill_candidato.nextval, ?, ?, ?)";
		
		PreparedStatement stm = conexao.prepareStatement(query, new String[] { "ID_SKILL_CAND" });
		stm.setInt(1, idCandidato);
		stm.setInt(2, skill.getId());
		stm.setInt(3, skill.getNivel());
		
		stm.executeUpdate();
		
		ResultSet result = stm.getGeneratedKeys();
		if(result.next()) 
			return result.getInt(1);
		
		throw new ErroOperacaoException("Não foi possivel realizar o cadastro");
	}
	
	public boolean removerSkillCandidato(int idSkill) throws SQLException, ErroOperacaoException {
		String query = "DELETE T_UNEXT_SKILL_CANDIDATO WHERE ID_SKILL = ?";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setInt(1, idSkill);
		
		if(stm.executeUpdate() < 1)
			throw new ErroOperacaoException("Nao foi possivel remover");
		
		return true;
	}

	public void mudarFoto(CandidatoTo candidato) throws SerialException, SQLException, ErroOperacaoException {
		byte[] decodedByte = Base64.getDecoder().decode(candidato.getUrlFoto());
		Blob blob = conexao.createBlob();
		blob.setBytes(1, decodedByte);
				
		String query = "UPDATE T_UNEXT_PESSOA SET FT_PERFIL = ? WHERE ID_PESSOA = ?";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setBlob(1, blob);
		stm.setInt(2, candidato.getIdPessoa());
		
		if(stm.executeUpdate() < 1) {
			blob.free();
			throw new ErroOperacaoException("Alterar foto falhou");
		}
		
		blob.free();
	}
}
