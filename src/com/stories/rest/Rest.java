package com.stories.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.stories.dao.UserDao;
import com.stories.utils.Secured;

@Path("/v1")
public class Rest{
	UserDao userdao = UserDao.getInstance();
	
	@POST
	@Path("/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response register(@FormParam("username") String username, @FormParam("password") String password, 
			@FormParam("firstName") String firstName, @FormParam("lastName") String lastName){
		try{
			userdao.register(username, password, firstName, lastName);
			return Response.ok().build();
		}catch(Exception e){
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response login(@FormParam("username") String username, @FormParam("password") String password){
		try{
			String token = userdao.authenticate(username, password);
			return Response.ok().header("token", token).build();
		}catch(Exception e){
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
	}
	
	@GET
	@Secured
	@Path("/stories")
	public Response getStories(){
		return Response.ok().build();
	}
	
	//for development purpose
	/* 
	@POST
	@Path("/delete")
	public Response delete(@FormParam("username") String username){
		userdao.removeUser(username);
		return Response.ok().build();
	}
	*/
}
