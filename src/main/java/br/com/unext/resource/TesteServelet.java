package br.com.unext.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/teste")
public class TesteServelet {

	@GET
	public String teste() {
		return "Ola Mundo";
	}
}
