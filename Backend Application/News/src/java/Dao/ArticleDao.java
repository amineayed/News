/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Hiber.NewHibernateUtil;
import entities.Article;
import entities.Category;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author amin ayed
 */
public class ArticleDao {
     private NewHibernateUtil helper;
 private Session session;
 public ArticleDao() {
 helper = new NewHibernateUtil();
 session = helper.getSessionFactory().openSession();
 }
 public void AddArticle(Article f) {

 session.beginTransaction();
 session.save(f);
 session.getTransaction().commit();
 }
 public void EditArticle(Article f) {
 session.beginTransaction();
 session.update(f);
 session.getTransaction().commit();
 }
 public Article FindArticle(int id) {
 session.beginTransaction();
 Article f;
 f = (Article) session.get(Article.class, id);
 return (f);
 }

 public void RemoveArticle(int id) {
 session.beginTransaction();
 Article f;
 f = FindArticle(id);
 session.delete(f);
 session.getTransaction().commit();
 }

 public List<Article> FindAll(){

 return session.createQuery("select a from Article a").list();
 }

 public void ExitSession(){
 session.close();
 }

 public static void AddArticleAdapter(ArrayList<Article> l){
     ArticleDao ad=new ArticleDao();
     for (int i=0;i<l.size();i++){
         
         ad.AddArticle(l.get(i));
     }
     
     ad.ExitSession();
     
 }
  public static void CleanArticleTable(){
     ArticleDao ad=new ArticleDao();
     ad.session.createSQLQuery("TRUNCATE TABLE article ").executeUpdate();
   
     
 }

     public List<Article> FindbyCategory(Category category){
    return session.createSQLQuery("select * from article a where a.category_title like '%"+category.getTitle()+"%'").list();
 }

    public List<Article> Findbytitle(String title){
    return session.createSQLQuery("select * from article a where a.Title like '%"+title+"%'").list();
 }
    
    
     public List<Article> Findbysource(String source){
    return session.createSQLQuery("select * from article a where a.source like '%"+source+"%'").list();
   
 }
     
       public List<Article> FindbyDescription(String description){
    return session.createSQLQuery("select * from article a where a.Description like '%"+description+"%'").list();
   
 }
       
            public List<Article> Findbydate(String date){
    return session.createSQLQuery("select * from article a where a.date like '%"+date+"%'").list();
   
 }
            
            public String fromListToJson(List<Article> articlesList){
                
                
                String json="";
                  
                json=json+"[";
                for(int i=0;i<articlesList.size()-2;i++){
                    json=json+(articlesList.get(i).toString())+",";
                    
                }
                json=json+articlesList.get(articlesList.size()-1).toString()+"]";
                return json;
            }
            
          
}
