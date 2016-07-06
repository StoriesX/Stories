package com.stories.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import com.stories.model.User;
import com.stories.dao.UserDao;

@Path("/v1")
public class Rest{
	UserDao userdao = UserDao.getInstance();
	
	@POST
	@Path("/register")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void register(@FormParam("username") String username, @FormParam("password") String password){
		User user = new User(username, password);
		System.out.println(userdao.register(user)?"added a new user":"already existed");
	}
	
	@POST
	@Path("/login")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void login(@FormParam("username") String username, @FormParam("password") String password){
		System.out.println(username+" "+password);
	}
	
}
