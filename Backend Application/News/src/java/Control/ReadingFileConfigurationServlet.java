/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Dao.UserDao;

import FileConfiguration.FileReaderConfig;
import entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Firass
 */
@WebServlet(name = "ReadingFileConfigurationServlet", urlPatterns = {"/ReadingFileConfigurationServlet"})
public class ReadingFileConfigurationServlet extends HttpServlet {

    

/*
    parms are file path and the user id
    */
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    User usr=new User(Integer.parseInt(request.getParameter("id").toString()));
        FileReaderConfig frc =new FileReaderConfig(request.getParameter("file").toString());
   
       frc.populatingCateg();
        for (int i=0; i<frc.getCategorylist().size();i++)
        {
            usr.addCategories(frc.getCategorylist().get(i));
        }
        
      UserDao upd=new UserDao();
       upd.EditUser(usr);
       upd.ExitSession();
            response.setContentType("text/html;charset=UTF-8");
 try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + usr.getCategories()+ "</h1>");
             out.println("<hr>");
             out.println("<h1>Servlet NewServlet at " + frc.getCategorylist()+ "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
      
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
      
       }
   

}
