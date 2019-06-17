package entities;
// Generated 29 avr. 2019 22:52:54 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Category generated by hbm2java
 */
public class Category  implements java.io.Serializable {


     private String title;
     private Set articles = new HashSet(0);
     private Set users = new HashSet(0);

    public Category() {
    }

	
    public Category(String title) {
        this.title = title;
    }
    public Category(String title, Set articles, Set users) {
       this.title = title;
       this.articles = articles;
       this.users = users;
    }
   
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public Set getArticles() {
        return this.articles;
    }
    
    public void setArticles(Set articles) {
        this.articles = articles;
    }
    public Set getUsers() {
        return this.users;
    }
    
    public void setUsers(Set users) {
        this.users = users;
    }

    @Override
    public String toString() {
       return "{\"title\":\""+this.title+"\"}";
    }




}


