package com.concretepage.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.concretepage.dao.ArticleDAO;
import com.concretepage.dao.IUserDAO;
import com.concretepage.dao.UserDAO;
import com.concretepage.entity.Article;
import com.concretepage.entity.User;
import com.concretepage.service.IUserService;

public class JerseyClient {
	public void getArticleDetails() {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8080/spring-app/article");
		WebTarget details = base.path("details");
		List<Article> list = details.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Article>>() {});
		
	    list.stream().forEach(article -> 
	        System.out.println(article.getArticleId()+", "+ article.getTitle()+", "+ article.getCategory()));
	    
	    client.close();
	}
	public void getArticleById(int articleId) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8080/spring-app/article");
		WebTarget articleById = base.path("{id}").resolveTemplate("id", articleId);
		Article article = articleById.request(MediaType.APPLICATION_JSON)
				.get(Article.class);
		
        System.out.println(article.getArticleId()+", "+ article.getTitle()+", "+ article.getCategory());
        
	    client.close();
	}
	public void addArticle(Article article) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8080/spring-app/article");
		WebTarget add = base.path("add");
		Response response = add.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(article));
		
		System.out.println("Response Http Status: "+ response.getStatus());
        System.out.println(response.getLocation());
        
	    client.close();
	}
	public void updateArticle(Article article) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8080/spring-app/article");
		WebTarget update = base.path("update");
		Response response = update.request(MediaType.APPLICATION_JSON)
				.put(Entity.json(article));
		
		System.out.println("Response Http Status: "+ response.getStatus());
		Article resArticle = response.readEntity(Article.class);
		System.out.println(resArticle.getArticleId()+", "+ resArticle.getTitle()+", "+ resArticle.getCategory());
        
	    client.close();
	}
	public void deleteArticle(int articleId) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8080/spring-app/article");
		WebTarget deleteById = base.path("{id}").resolveTemplate("id", articleId);
		Response response = deleteById.request(MediaType.APPLICATION_JSON)
				.delete();
		
		System.out.println("Response Http Status: "+ response.getStatus());
		if(response.getStatus() == 204) {
			System.out.println("Data deleted successfully.");
		}
        
	    client.close();
	}	
	
	public void addUser(User article) {
		Client client = ClientBuilder.newClient();
		WebTarget base = client.target("http://localhost:8080/spring-app/user");
		WebTarget add = base.path("signup");
		Response response = add.request(MediaType.APPLICATION_JSON)
				.post(Entity.json(article));
		
		System.out.println("Response Http Status: "+ response.getStatus());
        System.out.println(response.getLocation());
        
	    client.close();
	}
	public static void main(String[] args) {
		JerseyClient jerseyClient = new JerseyClient();
		
		User user = new User();
		user.setName("ijkl");
		user.setEmail("email.@gmail.com");
		user.setPhonenumber("657694534");
		user.setPassword("12345678");
		jerseyClient.addUser(user);
//	    jerseyClient.getArticleDetails();
//		//jerseyClient.getArticleById(102);
//		
//		Article article = new Article();
//		article.setTitle("Spring REST Security using Hibernate2");
//		article.setCategory("Spring"); 
//		//jerseyClient.addArticle(article);
//		
//		article.setArticleId(105);
//		//jerseyClient.updateArticle(article);
//		
//		//jerseyClient.deleteArticle(105);
		
//	IUserDAO userDAO = new IUserDAO() {
//		
//		@Override
//		public boolean userExists(String email, String phonenumber) {
//			// TODO Auto-generated method stub
//			return false;
//		}
//		
//		@Override
//		public void updateUser(User user) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//		@Override
//		public User getUserById(String email, String phonenumber) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//		
//		@Override
//		public User getUser(User user) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//		
//		@Override
//		public void deleteArticle(String articleId) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//		@Override
//		public User addUser(User user) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//	};
	
//	System.out.println(userDAO.getUserById("vignesh.s@tringapps.com","8056080768"));
//	User user = new User();
//	user.setEmail("vignesh.s@gmail.com");
//	user.setName("vw");
//	user.setPhonenumber("9500103448");
//	user.setPassword("1234578");
//	userDAO.addUser(user);
	
//	ArticleDAO articleDAO = new ArticleDAO();
//	
//	articleDAO.getAllArticles();
//		
	
		
	}
}
