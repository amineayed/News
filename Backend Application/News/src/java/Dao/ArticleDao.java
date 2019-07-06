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
import org.hibernate.Transaction;
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

    public void add_Favorite_Article(int id_user, int id_article) {
        Transaction t = session.beginTransaction();
        session.createQuery("Insert into favoritearticle(iduser,id_Article) select iduser,id_Article from user,article where iduser=:iduser and id_Article=:id_Article )").setParameter("iduser", id_user).setParameter("id_Article", id_article).executeUpdate();
        t.commit();
    }

    public List<Article> FindAll() {

        return session.createQuery("select a from Article a").list();
    }

    public void ExitSession() {
        session.close();
    }

    public void AddArticleAdapter(ArrayList<Article> ArticlesList) {
        ArticleDao ad = new ArticleDao();
        for (int i=0;i< ArticlesList.size();i++) {

            ad.AddArticle(ArticlesList.get(i));
            ad.ExitSession();
            ad = new ArticleDao();
        }

        

    }

    public  void CleanArticleTable() {
        
        session.createSQLQuery("SET FOREIGN_KEY_CHECKS = 0;").executeUpdate();
        session.createSQLQuery("TRUNCATE table article;").executeUpdate();
        session.createSQLQuery("SET FOREIGN_KEY_CHECKS = 1; ").executeUpdate();

    }

    public  void CleanFavoriteArticleTable() {
       
        session.createSQLQuery("TRUNCATE table favoritearticle;").executeUpdate();
        
        
    }

    public List<Article> FindbyCategory(Category category) {
        return session.createQuery("select a from Article a where category.title like :title").setParameter("title", "%" + category.getTitle() + "%").list();
    }

    public List<Article> Findbytitle(String title) {
        return session.createQuery("select a from Article a where title like :title").setParameter("title", "%" + title + "%").list();
    }

    public List<Article> Findbysource(String source) {
        return session.createQuery("select a from Article a where source like :source").setParameter("source", "%" + source + "%").list();

    }

    public List<Article> FindbyDescription(String description) {
        return session.createSQLQuery("select a from Article a where description like :description").setParameter("description", "%" + description + "%").list();

    }

    public List<Article> Findbydate(String date) {
        return session.createSQLQuery("select a from Article a where date like :date").setParameter("date", "%" + date + "%").list();

    }

    public String fromListToJson(List<Article> articlesList) {

        String json = "";
        Article article = null;

        json = json + "[";
        for (int i = 0; i < articlesList.size() - 1; i++) {
            article = (Article) articlesList.get(i);
            json = json + (article.toString()) + ",";

        }
        article = (Article) articlesList.get(articlesList.size() - 1);
        json = json + article.toString() + "]";
        return json;
    }

}
