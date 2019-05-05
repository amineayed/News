/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileConfiguration;

import Dao.CategoryDao;
import entities.Category;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Firass
 */
public class FileReaderConfig {
    private String filename;
    private  ArrayList<Category> categorylist;
   
   public FileReaderConfig(String filename)
   {
       this.filename=filename;
        categorylist=new ArrayList<Category>();
   }
   public void addElement(Category categ){
    this.categorylist.add(categ);
       }
   
   public void populatingCateg() throws FileNotFoundException, IOException{
       String line;
   FileReader file=new FileReader(this.filename); 
   BufferedReader BR=new BufferedReader(file);
   CategoryDao categdao=new CategoryDao();
List<Category> categdb=categdao.FindAll();
categdao.ExitSession();
   
   while((line=BR.readLine())!=null){
       
      if (!categdb.contains(line)){
  
           this.categorylist.add(new Category(line.toUpperCase()));
      }
   }
    BR.close(); 
   
   }
   public Set ConvertListToSet(){
   return new HashSet(this.categorylist);
   }

    public String getFilename() {
        return filename;
    }

    public ArrayList<Category> getCategorylist() {
        return categorylist;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setCategorylist(ArrayList<Category> categorylist) {
        this.categorylist = categorylist;
    }
   
   
   
}
