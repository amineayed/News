/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestServices;

import Adapter.RSSAdapter;
import com.fasterxml.jackson.core.JsonProcessingException;

import Dao.ArticleDao;
import Dao.CategoryDao;
import Dao.UserDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import entities.Article;
import entities.Category;
import entities.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
import javax.xml.parsers.ParserConfigurationException;
import org.json.JSONArray;
import org.xml.sax.SAXException;



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
    @Path("/Addfavorite/{id}/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Favorites(@PathParam("id") int id,@PathParam("category") String category){
        
        User user=users.FindUser(id);
        users.ExitSession();
        Category user_category=categorys.FindCategory(category);
       
        Set categories=new  HashSet(0);
        categories.add(user_category);
        user.setCategories(categories);
        users=new UserDao();
        users.EditUser(user);
       
        
        return Response.ok("{\"id\":5}").build();
    }
    
    
    @GET
    @Path("/reload")
    public void ReloadArticles() throws IOException, SAXException, ParserConfigurationException{
        ArticleDao.CleanArticleTable();
        ArticleDao.AddArticleAdapter(RSSAdapter.retrieveAllNews());
    }
    
    
    @GET
    @Path("/GetFavorites/{id}")
    public Response GetFavorites(@PathParam("id") int id){
        User user=users.FindUser(id);
        List<Article> FavoritArticles=new ArrayList<Article>();
       Object[] CategoriesArray = user.getCategories().toArray(new Category[user.getCategories().size()]);
        for(int i=0;i<CategoriesArray.length;i++){
        FavoritArticles.addAll(articles.FindbyCategory((Category) CategoriesArray[i]));
                }
       return  Response.ok(articles.fromListToJson(FavoritArticles)).build();
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
