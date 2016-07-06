package com.stories.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.stories.model.User;
import com.stories.dao.UserDao;

@Path("/v1")
public class Rest{
	UserDao userdao = UserDao.getInstance();
	
	@POST
	@Path("/register")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void register(@FormParam("username") String username, @FormParam("password") String password, 
			@FormParam("firstName") String firstName, @FormParam("lastName") String lastName, 
			@Context HttpServletResponse servletResponse) throws IOException{
		User user = new User(username, password, firstName, lastName);
		servletResponse.addHeader("result", String.valueOf(userdao.register(user)));
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void login(@FormParam("username") String username, @FormParam("password") String password,
			@Context HttpServletResponse servletResponse) throws IOException{
		User user = new User(username, password);
		servletResponse.addHeader("token", userdao.login(user));
	}
	
}
