package br.com.unext.resource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.rowset.serial.SerialException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.unext.bo.EmpresaBo;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.NaoEncontradoException;
import br.com.unext.factory.ConnectionFactory;
import br.com.unext.to.EmpresaTo;
import br.com.unext.to.EnderecoTo;

@Path("/empresa")
public class EmpresaResource {

	private EmpresaBo bo;
	private Connection conexao;
	
	public EmpresaResource() throws ClassNotFoundException, SQLException {
		this.conexao = ConnectionFactory.getConnection();
		this.bo = new EmpresaBo(conexao);
	}
	
	@GET
	@Path("/perfil/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response perfil(@PathParam("id") int id) {
		try {
			
			EmpresaTo empresa = bo.buscarById(id);
			return Response.ok(empresa).build();
			
		} catch (SQLException e) {
			return Response.status(500).build();
		} catch (ErroOperacaoException e) {
			return Response.status(500).build();
		} catch (NaoEncontradoException e) {
			return Response.status(404).build();
		}finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				return Response.status(500).build();
			}
		}
	}
	
	@PUT
	@Path("/perfil/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response atualizarPerfil(@PathParam("id") int id, EmpresaTo empresa) throws SQLException {
		try {
			
			bo.editar(empresa);
			conexao.commit();
			return Response.ok(202).build();
			
		} catch (SQLException e) {
			conexao.rollback();
			return Response.status(500).build();
		} catch (ErroOperacaoException e) {
			conexao.rollback();
			return Response.status(500).build();
		}finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				return Response.status(500).build();
			}
		}
	}
	
	@PUT
	@Path("/foto")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response mudarFoto(EmpresaTo empresa) throws SQLException {
		try {
			bo.mudarFoto(empresa);
			conexao.commit();
			return Response.status(202).build();
		} catch (SerialException e) {
			conexao.rollback();
			return Response.status(500).build();
		} catch (SQLException e) {
			conexao.rollback();
			return Response.status(500).build();
		} catch (ErroOperacaoException e) {
			conexao.rollback();
			return Response.status(500).build();
		}
		finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				return Response.status(500).build();
			}
		}
	}
	
	@POST
	@Path("/{id}/endereco")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response adicionarEndereco(@PathParam("id") int idEmpresa, EnderecoTo endereco) throws SQLException {
		try {
			bo.adicionarEndereco(idEmpresa, endereco);
			conexao.commit();
			return Response.status(201).build();
		} catch (SQLException e) {
			conexao.rollback();
			return Response.status(500).build();
		} catch (ErroOperacaoException e) {
			conexao.rollback();
			return Response.status(500).build();
		}finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				return Response.status(500).build();
			}
		}
	}
	
	@DELETE
	@Path("/endereco/{id}")
	public Response removerEndereco(@PathParam("id") int idEndereco) throws SQLException {
		try {
			bo.removerEndereco(idEndereco);
			conexao.commit();
			return Response.status(202).build();
		} catch (SQLException e) {
			conexao.rollback();
			return Response.status(500).build();
		} catch (ErroOperacaoException e) {
			conexao.rollback();
			return Response.status(500).build();
		}finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				return Response.status(500).build();
			}
		}
	}
}
