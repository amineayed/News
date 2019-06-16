/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestServices;

import com.fasterxml.jackson.core.JsonProcessingException;

import Dao.ArticleDao;
import Dao.CategoryDao;
import Dao.UserDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import entities.User;


import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.JSONArray;



/**
 * REST Web Service
 *
 * @author amin ayed
 */
@Path("/")
public class NewsServices {

    @Context
    private UriInfo context;
    
    private UserDao users;
    private CategoryDao categorys;
    private ArticleDao articles;
    private ObjectMapper objectMapper;
    

    /**
     * Creates a new instance of NewsServices
     */
    public NewsServices() {
        if(users==null){
            users=new UserDao();
           
        }
        
        if (articles==null){
            articles=new ArticleDao();
        }
        if(categorys==null){
            categorys=new CategoryDao();
        }
        
        objectMapper=new ObjectMapper();
        
    }
    
    
    @GET
    @Path("/articles/all")
    @Produces(MediaType.APPLICATION_JSON)
    
    public Response getArticles() throws JsonProcessingException{
       
   ;
     
String ArticlesListJson = articles.fromListToJson(articles.FindAll());
     
        
        
        
       
   
        return Response.ok(ArticlesListJson).build();
        
    }
    
    
    @GET
    @Path("/login/{login}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("login") String login,@PathParam("password") String password){
        User user=users.GetUser(login, password);
        
        
            return Response.ok(user.toString()).build();
        
    }
    
    
    @GET
    @Path("/user/add/{login}/{password}/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response AddUser(@PathParam("login") String login,@PathParam("password") String password,@PathParam("email") String email){
        User user=new User(login, password, email);
       user= users.AddUser(user);
         return Response.ok(user.toString()).build();
    }

    
    @GET
    @Path("/favorite/{id}/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Favorites(@PathParam("id") int id,@PathParam("category") String category){
        
        return Response.ok().build();
    }

    /**
     * PUT method for updating or creating an instance of NewsServices
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
