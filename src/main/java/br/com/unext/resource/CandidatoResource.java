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

import br.com.unext.bo.CandidatoBo;
import br.com.unext.exceptions.ErroOperacaoException;
import br.com.unext.exceptions.NaoEncontradoException;
import br.com.unext.factory.ConnectionFactory;
import br.com.unext.to.CandidatoTo;
import br.com.unext.to.ContatoTo;
import br.com.unext.to.FormacaoAcademicaTo;
import br.com.unext.to.SkillTo;

@Path("/candidato")
public class CandidatoResource {

	private CandidatoBo bo;
	private Connection conexao;
	
	public CandidatoResource() throws ClassNotFoundException, SQLException {
		this.conexao = ConnectionFactory.getConnection();
		this.bo = new CandidatoBo(conexao);
	}
	
	@GET
	@Path("/perfil/{id}")
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
	@Path("/perfil/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizarPerfil(@PathParam("id") int id, CandidatoTo candidato) throws SQLException {
		try {
			
			if(bo.atualizarCandidato(candidato))
				conexao.commit();
			else
				throw new Exception();
			
			return Response.status(202).build();
		} catch (SQLException e) {
			conexao.rollback();
			return Response.status(500).build();
		} catch (ErroOperacaoException e) {
			conexao.rollback();
			return Response.status(500).build();
		} catch (Exception e) {
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
	public Response mudarFoto(CandidatoTo candidato) {
		try {
			bo.mudarFoto(candidato);
			conexao.commit();
			return Response.status(202).build();
		} catch (Exception e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
			}
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
	@Path("/{id}/contato")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response adicionarContato(@PathParam("id") int idCandidato, ContatoTo contato) {
		try {
			bo.adicionarContato(idCandidato, contato);
			conexao.commit();
			return Response.status(201).build();
		} catch (SQLException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
			}
			return Response.status(500).build();
		} catch (NaoEncontradoException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
			}
			return Response.status(404).build();
		} catch (ErroOperacaoException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
			}
			return Response.status(400).build();
		}finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				return Response.status(500).build();
			}
		}
	}
	
	
	@DELETE
	@Path("/contato/{id}")
	public Response removerContato(@PathParam("id") int idContato) {
		
		try {
			bo.removerContato(idContato);
			conexao.commit();
			return Response.status(202).build();
		}  catch (SQLException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
			}
			return Response.status(500).build();
		} catch (ErroOperacaoException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
			}
			return Response.status(400).build();
		}finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				return Response.status(500).build();
			}
		}
	}
	
	@POST
	@Path("/{id}/formacao")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response adicionarFormacao(@PathParam("id") int idCandidato, FormacaoAcademicaTo formacao) {
		try {
			bo.adicionarFormacao(idCandidato, formacao);
			conexao.commit();
			return Response.status(201).build();
		}  catch (SQLException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
			}
			return Response.status(500).build();
		} catch (ErroOperacaoException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
			}
			return Response.status(400).build();
		}finally {
			try {
				conexao.close();
			} catch (SQLException e) {
				return Response.status(500).build();
			}
		}
	}
	
	
	@DELETE
	@Path("/formacao/{id}")
	public Response removerFormacao(@PathParam("id") int idFormacao) {
		
		try {
			bo.removerFormacao(idFormacao);
			conexao.commit();
			return Response.status(202).build();
		} catch (SQLException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
			}
			return Response.status(500).build();
		} catch (ErroOperacaoException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
			}
			return Response.status(400).build();
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
	public Response adicionarSkill(@PathParam("id") int idCandidato, SkillTo skill) {
		try {
			
			bo.adicionarSkill(idCandidato, skill);
			conexao.commit();
			return Response.status(201).build();
			
		} catch (SQLException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
			}
			return Response.status(500).build();
		} catch (ErroOperacaoException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
			}
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
	@Path("/skill/{id}")
	public Response removerSkill(@PathParam("id") int idSkill) {
		try {
			bo.removerSkillCandidato(idSkill);
			conexao.commit();
			return Response.status(202).build();
		} catch (SQLException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return Response.status(500).build();
		} catch (ErroOperacaoException e) {
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return Response.status(500).build();
		}
	}
}