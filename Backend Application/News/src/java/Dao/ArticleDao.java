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
import java.util.List;
import org.hibernate.Session;

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
 public Article FindArticle(String id) {
 session.beginTransaction();
 Article f;
 f = (Article) session.get(Article.class, id);
 return (f);
 }

 public void RemoveArticle(String id) {
 session.beginTransaction();
 Article f;
 f = FindArticle(id);
 session.delete(f);
 session.getTransaction().commit();
 }

 public List<Article> FindAll(){

 return session.createQuery("select a from article a").list();
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

    
}
