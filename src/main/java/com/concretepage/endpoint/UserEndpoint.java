package com.concretepage.endpoint;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.concretepage.entity.User;
import com.concretepage.service.IUserService;
import com.library.Library;


@Component
@Path("/user")
public class UserEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(ArticleEndpoint.class);	
	@Autowired
	private IUserService us;


	@POST
	@Path("/signup")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response signUp(User user,@Context ServletContext context) {
		logger.info(user.toString());
		if (us.addUser(user)) {
			user = us.getUser(user);
			return Response.ok(user).build();
		}

		return Response.status(Status.CONFLICT).build();
	}

	@POST
	@Path("/signin")
	@Produces(MediaType.APPLICATION_JSON)
	public Response signIn(User user) {

		User dbUser = new User();

		if (user.getEmail() != null && !user.getEmail().isEmpty())
			dbUser = us.getUser(user);
		else if (user.getEmail() == null && user.getPhonenumber() != null || user.getPhonenumber().isEmpty())
			dbUser = us.getUserById(user.getEmail(), user.getPhonenumber());

		if (dbUser == null) {
			return Response.status(Status.CONFLICT).build();
		}

		if (user.getPassword().equalsIgnoreCase(dbUser.getPassword()))
			return Response.ok(dbUser).build();
		else
			return Response.status(Status.CONFLICT).build();

	}
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String check() {
		
		return "hello";
		
	}
	
	
//	@POST
//	@Path("/signuppic")
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.MULTIPART_FORM_DATA)
//	public Response signUpPic(@FormDataParam("file") InputStream fileInputStream,            
//            @FormDataParam("user") User user) {
//		try {
//			user.setProfimage(Library.getBytesFromStream(fileInputStream));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		logger.info(user.toString());
//		if (us.addUser(user)) {
//			user = us.getUser(user);
//			return Response.ok(user).build();
//		}
//
//		return Response.status(Status.CONFLICT).build();
//	}

}
