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
import br.com.unext.to.EmpresaTo;
import br.com.unext.to.UsuarioTo;

public class EmpresaDao implements IDao<EmpresaTo> {

	private Connection conexao;

	public EmpresaDao(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public int cadastrar(EmpresaTo model) throws SQLException, ErroOperacaoException {
		String query = "INSERT INTO T_UNEXT_EMPRESA (ID_EMPRESA, ID_USUARIO, NM_EMPRESA, NM_RAZAO_SOCIAL, NR_CNPJ) "
				+ "VALUES (SQ_T_UNEXT_EMPRESA.nextval, ?, ?, ?, ?)";

		PreparedStatement stm = conexao.prepareStatement(query, new String[] { "ID_EMPRESA" });
		stm.setInt(1, model.getUsuario().getId());
		stm.setString(2, model.getNome());
		stm.setString(3, model.getRazaoSocial());
		stm.setString(4, model.getCnpj());

		stm.executeUpdate();

		ResultSet result = stm.getGeneratedKeys();
		if (result.next()) {
			model.setId(result.getInt(1));
			return model.getId();
		}

		throw new ErroOperacaoException("Não foi possivel realizar o cadastro");
	}

	@Override
	public boolean editar(EmpresaTo model) throws SQLException, ErroOperacaoException {
		String query = "UPDATE "
				+ "    T_UNEXT_EMPRESA "
				+ "SET "
				+ "    NM_EMPRESA = ?, "
				+ "    NM_RAZAO_SOCIAL = ?, "
				+ "    NR_CNPJ = ?, "
				+ "    DS_ATUACAO = ?, "
				+ "    DS_EMPRESA = ? "
				+ "WHERE "
				+ "    ID_EMPRESA = ?";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		
		stm.setString(1, model.getNome());
		stm.setString(2, model.getRazaoSocial());
		stm.setString(3, model.getCnpj());
		stm.setString(4, model.getAtuacao());
		stm.setString(5, model.getDescricao());
		stm.setInt(6, model.getId());
		
		if(stm.executeUpdate() < 1)
			throw new ErroOperacaoException("Nao foi possivel editar a empresa");
		
		return true;
	}

	@Override
	public boolean remover(int id) throws SQLException, ErroOperacaoException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<EmpresaTo> listar() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EmpresaTo buscarById(int id) throws SQLException, ErroOperacaoException, NaoEncontradoException {
		String query = "SELECT  "
					+ "    T_UNEXT_EMPRESA.ID_EMPRESA ID_EMPRESA, "
					+ "    T_UNEXT_EMPRESA.NM_EMPRESA NM_EMPRESA, "
					+ "    T_UNEXT_EMPRESA.NM_RAZAO_SOCIAL NM_RAZAO_SOCIAL, "
					+ "    T_UNEXT_EMPRESA.FT_PERFIL FT_PERFIL, "
					+ "    T_UNEXT_EMPRESA.NR_CNPJ NR_CNPJ, "
					+ "    T_UNEXT_EMPRESA.DS_ATUACAO DS_ATUACAO, "
					+ "    T_UNEXT_EMPRESA.DS_EMPRESA DS_EMPRESA, "
					+ "    T_UNEXT_EMPRESA.ST_STATUS ST_STATUS, "
					+ "     "
					+ "    T_UNEXT_USUARIO.ID_USUARIO ID_USUARIO, "
					+ "    T_UNEXT_USUARIO.DS_USER DS_USER, "
					+ "    T_UNEXT_USUARIO.DS_SENHA DS_SENHA  "
					+ "FROM  "
					+ "    T_UNEXT_EMPRESA  "
					+ "    INNER JOIN T_UNEXT_USUARIO   "
					+ "        ON T_UNEXT_EMPRESA.ID_USUARIO = T_UNEXT_USUARIO.ID_USUARIO "
					+ "WHERE "
					+ "    ID_EMPRESA = ?";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setInt(1, id);
		
		ResultSet result = stm.executeQuery();
		
		if(result.next()) {
			EmpresaTo empresa = new EmpresaTo();
			empresa.setUsuario(new UsuarioTo());
			
			empresa.setId(result.getInt("ID_EMPRESA"));
			empresa.setNome(result.getString("NM_EMPRESA"));
			empresa.setRazaoSocial(result.getString("NM_RAZAO_SOCIAL"));
			empresa.setCnpj(result.getString("NR_CNPJ"));
			empresa.setAtuacao(result.getString("DS_ATUACAO"));
			empresa.setDescricao(result.getString("DS_EMPRESA"));
			empresa.setStatus(result.getString("ST_STATUS").charAt(0));
			
			Blob blob = result.getBlob("FT_PERFIL");
			if(blob != null) {
				//convertendo o blob em bytes
				int blobLength = (int) blob.length();  
				byte[] blobAsBytes = blob.getBytes(1, blobLength);
				blob.free();
				
				//bytes para base64
				String base64 = Base64.getEncoder().encodeToString(blobAsBytes);
				
				empresa.setFotoEmpresa(base64);				
			}
			
			empresa.getUsuario().setId(result.getInt("ID_USUARIO"));
			empresa.getUsuario().setLogin(result.getString("DS_USER"));
			empresa.getUsuario().setSenha(result.getString("DS_SENHA"));
			
			return empresa;
		}

		throw new NaoEncontradoException("Empresa nao encontrada");
	}
	
	public int cadastrarEndereco(int idEmpresa, int idEndereco) throws SQLException, ErroOperacaoException {
		String query = "INSERT INTO T_UNEXT_ENDERECO_EMPRESA (ID_ENDERECO_EMPRESA, ID_EMPRESA, ID_ENDERECO) "
				+ "VALUES (SQ_T_UNEXT_ENDERECO_EMPRESA.nextval, ?, ?)";

		PreparedStatement stm = conexao.prepareStatement(query, new String[] { "ID_ENDERECO_EMPRESA" });
		stm.setInt(1, idEmpresa);
		stm.setInt(2, idEndereco);
		stm.executeUpdate();

		ResultSet result = stm.getGeneratedKeys();
		if (result.next()) {
			return result.getInt(1);
		}

		throw new ErroOperacaoException("Não foi possivel realizar o cadastro");
	}

	public void mudarFoto(EmpresaTo empresa) throws SerialException, SQLException, ErroOperacaoException {
		byte[] decodedByte = Base64.getDecoder().decode(empresa.getFotoEmpresa());
		Blob blob = conexao.createBlob();
		blob.setBytes(1, decodedByte);
				
		String query = "UPDATE T_UNEXT_EMPRESA SET FT_PERFIL = ? WHERE ID_EMPRESA = ?";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setBlob(1, blob);
		stm.setInt(2, empresa.getId());
		
		if(stm.executeUpdate() < 1) {
			blob.free();
			throw new ErroOperacaoException("Alterar foto falhou");
		}
		
		blob.free();
	}

	public boolean removerEndereco(int idEndereco) throws SQLException, ErroOperacaoException {
		String query = "DELETE T_UNEXT_ENDERECO_EMPRESA WHERE ID_ENDERECO = ?";
		
		PreparedStatement stm = conexao.prepareStatement(query);
		stm.setInt(1, idEndereco);
		
		if(stm.executeUpdate() < 1)
			throw new ErroOperacaoException("Nao foi possivel remover o endereco");
		
		return true;
	}
}
