package entities;
// Generated 21 juin 2019 16:21:59 by Hibernate Tools 4.3.1

import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

    private Integer iduser;
    private String login;
    private String password;
    private String mail;
    public Set articles = new HashSet(0);
    private Set categories = new HashSet(0);
    private String file;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public User() {
    }

    public User(String login, String password, String mail, Set articles, Set categories) {
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.articles = articles;
        this.categories = categories;
    }

    public User(String login, String password, String mail, String file) {
        this.login = login;
        this.password = password;
        this.mail = mail;
        this.file = file;
    }

    public User(Integer iduser) {
        this.iduser = iduser;
    }

    public User(String login, String password, String mail) {
        this.login = login;
        this.password = password;
        this.mail = mail;
    }

    public Integer getIduser() {
        return this.iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return this.mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Set getArticles() {
        return this.articles;
    }

    public void setArticles(Set articles) {
        this.articles = articles;
    }

    public Set getCategories() {
        return this.categories;
    }

    public void setCategories(Set categories) {
        this.categories = categories;
    }

    public void addCategories(Category categ) {
        this.getCategories().add(categ);
    }

    @Override
    public String toString() {
        return "{ \"iduser\" : " + this.iduser + ", \"login\" : \"" + this.login + "\", \"password\" : \"" + this.password + "\", \"mail\" : \"" + this.mail + "\"}";
    }
    
  

}
