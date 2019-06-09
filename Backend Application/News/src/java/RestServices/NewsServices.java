/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestServices;

import com.fasterxml.jackson.core.JsonProcessingException;
import Adapter.RSSAdapter;
import Dao.ArticleDao;
import Dao.CategoryDao;
import Dao.UserDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.parsers.ParserConfigurationException;
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
    public Response getCustomers() throws JsonProcessingException, IOException, SAXException, ParserConfigurationException{
       
    
      
        String ArticlesListJson = objectMapper.writeValueAsString(articles.FindAll());
        
        
        return Response.ok(ArticlesListJson).build();
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
