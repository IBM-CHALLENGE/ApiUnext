package br.com.unext.resource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.unext.bo.UsuarioBo;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.JaExistenteException;
import br.com.unext.factory.ConnectionFactory;
import br.com.unext.to.CandidatoTo;
import br.com.unext.to.EmpresaTo;

@Path("/usuario")
public class UsuarioServelet {

	private UsuarioBo usuarioBo;
	private Connection conexao;

	public UsuarioServelet() {
		try {
			conexao = ConnectionFactory.getConnection();

			usuarioBo = new UsuarioBo(conexao);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@POST
	@Path("/candidato")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@JsonIgnoreProperties(ignoreUnknown = true)
	public Response cadastrarUsuarioCandidato(CandidatoTo candidato) {

		try {
			usuarioBo.cadastrarUsuarioCandidato(candidato);
			conexao.commit();
			return Response.status(201).build();

		} catch (JaExistenteException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				return Response.status(500).build();
			}
			return Response.status(409).build();

		} catch (ErroOperacaoException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				return Response.status(500).build();
			}
			return Response.status(400).build();

		} catch (SQLException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				return Response.status(500).build();
			}
			return Response.status(500).build();

		} finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				return Response.status(500).build();
			}
		}

	}

	@POST
	@Path("/empresa")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cadastrarUsuarioEmpresa(EmpresaTo empresa) {

		try {
			usuarioBo.cadastrarUsuarioEmpresa(empresa);
			conexao.commit();
			return Response.status(201).build();

		} catch (JaExistenteException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				return Response.status(500).build();
			}
			return Response.status(409).build();

		} catch (ErroOperacaoException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				return Response.status(500).build();
			}
			return Response.status(400).build();

		} catch (SQLException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				return Response.status(500).build();
			}
			return Response.status(500).build();

		} finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				return Response.status(500).build();
			}
		}
		
	}
}
