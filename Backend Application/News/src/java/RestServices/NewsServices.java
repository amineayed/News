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

import entities.Article;
import entities.Category;
import entities.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import net.sf.ehcache.store.compound.factories.AATreeSet;

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
        if (users == null) {
            users = new UserDao();

        }

        if (articles == null) {
            articles = new ArticleDao();
        }
        if (categorys == null) {
            categorys = new CategoryDao();
        }

        objectMapper = new ObjectMapper();

    }

    @GET
    @Path("/articles/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getArticles() throws JsonProcessingException {
        String ArticlesListJson = articles.fromListToJson(articles.FindAll());
        return Response.ok(ArticlesListJson).build();
    }

    @GET
    @Path("/login/{login}/{password}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@PathParam("login") String login, @PathParam("password") String password) {
        User user = users.GetUser(login, password);
        if (user != null) {
            return Response.ok(user.toString()).build();
        } else {

            return Response.ok("{\"message\":\"username or password invalide !\"}").build();
        }
    }

    @GET
    @Path("/user/add/{login}/{password}/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response AddUser(@PathParam("login") String login, @PathParam("password") String password, @PathParam("email") String email) {
        int emailTest = users.findbyEmail(email);
        int LoginTest = users.findbyLogin(login);

        users.ExitSession();
        if (emailTest == 0 && LoginTest == 0) {
            User user = new User(login, password, email);
            users = new UserDao();

            user = users.AddUser(user);
            return Response.ok(user.toString()).build();
        } else {
            return Response.ok("{\"iduser\":0}").build();
        }
    }

    @GET
    @Path("/Addfavorite/{id}/{category}")
    public Response Favorites(@PathParam("id") int id, @PathParam("category") String category) {
        User user = users.FindUser(id);
        users.ExitSession();
        Category user_category = categorys.FindCategory(category);

        user.getCategories().add(user_category);
        users = new UserDao();
        users.EditUser(user);
        return Response.ok().build();
    }

    @GET
    @Path("/reload")
    @Produces(MediaType.APPLICATION_JSON)
    public Response ReloadArticles() throws IOException, SAXException, ParserConfigurationException {
 articles=new ArticleDao();
        articles.CleanFavoriteArticleTable();
        articles.ExitSession();
        articles = new ArticleDao();
        articles=new ArticleDao();
        articles.CleanArticleTable();
        articles.ExitSession();
        articles.AddArticleAdapter(RSSAdapter.retrieveAllNews());
        articles.ExitSession();
        return Response.ok("`{\"msg\":\"ok\"}").build();

    }

    

  
    @GET
    @Path("/GetFavoriteArticles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFavoriteArticles(@PathParam("id") int id) {
        User user = users.FindUser(id);
        Set ArticlesSet = user.getArticles();
        if (ArticlesSet.isEmpty()) {
            return Response.ok("{\"message\":\"No Favorite Articles !\"}").build();
        } else {
            String JSONResult = "";
            JSONResult = users.fromListToJsonArticles(ArticlesSet);
            return Response.ok(JSONResult).build();
        }

    }

    @GET
    @Path("/RemoveFavoriteArticle/{userId}/{articleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response RemoveFavoriteArticle(@PathParam("userId") int userId, @PathParam("articleId") int articleId) {
        User user = users.FindUser(userId);

        Article article = articles.FindArticle(articleId);

        boolean test = article.removeUser(user);

        articles.ExitSession();
        articles = new ArticleDao();
        articles.EditArticle(article);
        return Response.ok("{\"msg\":" + test + "}").build();

    }

    @GET
    @Path("/GetCategoriesedArticles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetFavorites(@PathParam("id") int id) {
        User user = users.FindUser(id);
        List<Article> FavoritArticles = new ArrayList<>();
        Set<Category> set = user.getCategories();
        if (set.isEmpty() != true) {
            List<Category> CategoriesArray = new ArrayList<>(set);

            for (int i = 0; i < CategoriesArray.size(); i++) {
                articles = new ArticleDao();
                FavoritArticles.addAll((List<Article>) articles.FindbyCategory(CategoriesArray.get(i)));
                articles.ExitSession();
            }
            String json;
            json = articles.fromListToJson(FavoritArticles);
            return Response.ok(json).build();
        } else {
            return Response.ok("{\"message\":\"No Category chosen !\"}").build();
        }
    }

    @GET
    @Path("/setfavorites/{id}/{articleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setFavoriteArticle(@PathParam("id") int id, @PathParam("articleId") int articleId) {

        int exist = users.getArticle(articleId, id);
        users.ExitSession();

        if (exist == 0) {
            users = new UserDao();
            User user = users.FindUser(id);
            Article article = articles.FindArticle(articleId);

            user.getArticles().add(article);
            users.ExitSession();

            users = new UserDao();
            users.EditUser(user);
            users.ExitSession();
            return Response.ok("{\"message\":\"ok\"}").build();

        } else {
            return Response.ok("{\"message\":\"This article is already a favorite one !\"}").build();

        }

    }

    @GET
    @Path("/user/update/{id}/{login}/{password}/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response ProfileUpload(@PathParam("id") int id, @PathParam("login") String login, @PathParam("password") String password, @PathParam("email") String email) {
        User user = users.FindUser(id);
        List<Article> l = new ArrayList<>();
        if (login != "") {
            user.setLogin(login);
        }
        if (password != "") {
            user.setPassword(password);
        }
        if (email != "") {
            user.setMail(email);
        }
        users.ExitSession();
        users = new UserDao();
        users.EditUser(user);
        return Response.ok(user.toString()).build();
    }

    /**
     * PUT method for updating or creating an instance of NewsServices
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @GET
    @Path("/articles/categorized")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategorizedArticles() throws JsonProcessingException, IOException {
        ArrayList<String> categories = new ArrayList<String>();
        FileConfiguration.FileReaderConfig fileReader = new FileConfiguration.FileReaderConfig("C:\\Users\\amin ayed\\Desktop\\file.txt");
        fileReader.populatingCateg();

        String ArticlesListJson = "";
        ArrayList<Article> articleList = new ArrayList<>();
        for (int i = 0; i < fileReader.getCategorylist().size(); i++) {
            articleList.addAll(articles.FindbyCategory(fileReader.getCategorylist().get(i)));
        }
        ArticlesListJson = articles.fromListToJson(articleList);
        return Response.ok(ArticlesListJson).build();
    }

    @GET
    @Path("/fileupload/{id}/{categories}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetCateg(@PathParam("id") int id, @PathParam("categories") String categories) {
        User usr = users.FindUser(id);
        users.ExitSession();
        usr.setFile(categories);

        String[] tabcateg = new String[12];
        tabcateg = categories.split("\\.");
        String aux = "";
        Set userCategories = new HashSet(0);
        for (int i = 0; i < tabcateg.length; i++) {
            aux = tabcateg[i];
            if (aux.contains("ON")) {
                userCategories.add(new Category(aux.substring(0, aux.indexOf(":"))));
                System.out.println(aux.substring(0, aux.indexOf(":")));
            }
        }
        usr.setCategories(userCategories);
        users = new UserDao();
        users.EditUser(usr);

        return null;

    }

    @GET
    @Path("/getcategfile/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response GetCateg(@PathParam("id") int id) {
        User usr = users.FindUser(id);
        String jsonstring = "";
            if(usr.getFile()!=null){
       jsonstring = "[" + usr.getFile() + "]";
            }else{
                jsonstring="AFRICA:OFF.\n" +
"AMERICAS:OFF.\n" +
"ASIA:OFF.\n" +
"BUSINESS:OFF.\n" +
"ENTERTAINMENT:OFF.\n" +
"EUROPE:OFF.\n" +
"MIDDLE_EAST:OFF.\n" +
"SCIENCE:OFF.\n" +
"SPORT:OFF.\n" +
"TECHNOLOGY:OFF.\n" +
"TOP_STORIES:OFF.\n" +
"WORLD:OFF";
            }
        return Response.ok(jsonstring).build();

    }
}
