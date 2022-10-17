package br.com.unext.resource;

import java.sql.Connection;
import java.sql.SQLException;

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

import br.com.unext.bo.VagaBo;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.NaoEncontradoException;
import br.com.unext.factory.ConnectionFactory;
import br.com.unext.to.SkillTo;
import br.com.unext.to.VagaTo;

@Path("/vaga")
public class VagaResource {

	private Connection conexao;
	private VagaBo bo;
	
	public VagaResource() throws ClassNotFoundException, SQLException {
		this.conexao = ConnectionFactory.getConnection();
		this.bo = new VagaBo(conexao);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listar() {
		try {
			
			return Response.ok(bo.listar()).build();
					
		} catch (SQLException e) {
			return Response.status(500).build();
		} catch (ErroOperacaoException e) {
			return Response.status(400).build();
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
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarById(@PathParam("id") int id) {
		try {
			
			return Response.ok(bo.buscarById(id)).build();
					
		} catch (SQLException e) {
			return Response.status(500).build();
		} catch (ErroOperacaoException e) {
			return Response.status(400).build();
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
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(VagaTo vaga) throws SQLException {
		try {
			
			bo.cadastrar(vaga);
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
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response editar(VagaTo vaga) throws SQLException {
		try {
			bo.editar(vaga);
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
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response remover(@PathParam("id") int id) throws SQLException {
		try {
			
			bo.remover(id);
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
	
	@GET
	@Path("/empresa/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listarVagasEmpresa(@PathParam("id") int idEmpresa) {
		
		try {
			
			return Response.ok(bo.listarByIdEmpresa(idEmpresa)).build();
		
		} catch (SQLException e) {
			return Response.status(500).build();
		} catch (ErroOperacaoException e) {
			return Response.status(500).build();
		} catch (NaoEncontradoException e) {
			return Response.status(500).build();
		}finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				return Response.status(500).build();
			}
		}
	}
	
	@POST
	@Path("/{id}/skill")
	@Produces(MediaType.APPLICATION_JSON)
	public Response adicionarSkill(@PathParam("id") int idVaga, SkillTo skill) throws SQLException {
		try {
			
			bo.adicionarSkill(idVaga, skill);
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
	
	
	@DELETE
	@Path("/{idVaga}/skill/{idSkill}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removerSkill(@PathParam("idVaga") int idVaga, @PathParam("idSkill") int idSkill) throws SQLException {
		try {
			
			bo.removerSkill(idVaga, idSkill);
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
