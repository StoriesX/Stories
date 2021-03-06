package com.stories.utils;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.stories.dao.UserDao;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
	UserDao userdao = UserDao.getInstance();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        try {
            userdao.validate(requestContext.getHeaderString("Token"), requestContext.getHeaderString("Username"));
        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
