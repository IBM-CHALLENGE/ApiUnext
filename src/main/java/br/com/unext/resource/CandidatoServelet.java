package br.com.unext.resource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.unext.bo.CandidatoBo;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.NaoEncontradoException;
import br.com.unext.factory.ConnectionFactory;
import br.com.unext.to.CandidatoTo;

@Path("/candidato")
public class CandidatoServelet {

	private CandidatoBo bo;
	private Connection conexao;
	
	public CandidatoServelet() throws ClassNotFoundException, SQLException {
		conexao = ConnectionFactory.getConnection();
		bo = new CandidatoBo(conexao);
	}
	
	@GET
	@Path("/perfil/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response perfil(@PathParam("id") int id) {
		
		try {
			CandidatoTo candidato = bo.buscarById(id);
			return Response.ok(candidato).build();
		} catch (Exception e) {
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
	@Path("/{id}/foto")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response foto(@PathParam("id") int id, Foto foto) {
		try {
			bo.mudarFoto(foto.getFoto(), id);
			return Response.status(202).build();
		} catch (Exception e) {
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

class Foto{
	private String foto;

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
}