/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Hiber.NewHibernateUtil;
import entities.Category;
import entities.User;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Firass
 */
public class UserDao {
    private NewHibernateUtil helper;
 private Session session;
 
 
 public UserDao() {
 helper = new NewHibernateUtil();
 session = helper.getSessionFactory().openSession();
 }
 public void AddUser(User f) {

 session.beginTransaction();
 session.save(f);
 session.getTransaction().commit();
 }
 public void EditUser(User f) {
 session.beginTransaction();
 session.update(f);
 session.getTransaction().commit();
 }
 public User FindUser(String id) {
 session.beginTransaction();
 User f=null;
 f = (User) session.get(User.class, id);
 return (f);
 }

 public void RemoveUser(String id) {
 session.beginTransaction();
 User f;
 f = FindUser(id);
 session.delete(f);
 session.getTransaction().commit();
 }

 public List<Category> FindAll(){

 return session.createSQLQuery("select * from user ").list();
 }

 public void ExitSession(){
 session.close();
 }

 public List<User> findbyMail(String mail){
     return session.createSQLQuery("select * from user where mail like '%"+mail+"%'").list();
 }



}
