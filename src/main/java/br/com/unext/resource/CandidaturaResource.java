package br.com.unext.resource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.unext.bo.CandidaturaBo;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.factory.ConnectionFactory;
import br.com.unext.to.CandidaturaTo;

@Path("/candidatura")
public class CandidaturaResource {

	private Connection conexao;
	private CandidaturaBo bo;
	
	public CandidaturaResource() throws ClassNotFoundException, SQLException {
		this.conexao = ConnectionFactory.getConnection();
		this.bo = new CandidaturaBo(conexao);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(CandidaturaTo candidatura) {
		
		try {
			bo.cadastrar(candidatura);
			conexao.commit();
			return Response.status(201).build();
			
		} catch (SQLException e) {
			return Response.status(500).build();
		} catch (ErroOperacaoException e) {
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
	@Path("/candidato/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarByIdCandidato(@PathParam("id") int idCandidato) {
		try {
			
			return Response.ok(bo.buscarByIdCandidato(idCandidato)).build();
					
		} catch (SQLException e) {
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
	public Response buscarByIdEmpresa(@PathParam("id") int idEmpresa) {
		try {
			
			return Response.ok(bo.buscarByIdEmpresa(idEmpresa)).build();
					
		} catch (SQLException e) {
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
