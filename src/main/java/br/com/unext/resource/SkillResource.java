package br.com.unext.resource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.unext.bo.SkillBo;
import br.com.unext.factory.ConnectionFactory;

@Path("/skill")
public class SkillResource {
	
	private SkillBo bo;
	private Connection conexao;
	
	public SkillResource() throws ClassNotFoundException, SQLException {
		conexao = ConnectionFactory.getConnection();
		bo = new SkillBo(conexao);
	}
	
	@GET
	@Path("/glossary")
	@Produces(MediaType.APPLICATION_JSON)
	public Response gloassyHardSkills() {
		
		try {
			return Response.ok(bo.listarHardSkills()).build();
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
