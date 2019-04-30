/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Hiber.NewHibernateUtil;
import entities.Category;
import java.util.List;
/*import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;*/
import org.hibernate.Session;
public class CategoryDao {
 private NewHibernateUtil helper;
 private Session session;
 
 
 public CategoryDao() {
 helper = new NewHibernateUtil();
 session = helper.getSessionFactory().openSession();
 }
 public void AddCategory(Category f) {

 session.beginTransaction();
 session.save(f);
 session.getTransaction().commit();
 }
 public void EditCategory(Category f) {
 session.beginTransaction();
 session.update(f);
 session.getTransaction().commit();
 }
 public Category FindCategory(String id) {
 session.beginTransaction();
 Category f;
 f = (Category) session.get(Category.class, id);
 return (f);
 }

 public void RemoveCategory(String id) {
 session.beginTransaction();
 Category f;
 f = FindCategory(id);
 session.delete(f);
 session.getTransaction().commit();
 }

 public List<Category> FindAll(){

 return session.createQuery("select c from category c").list();
 }

 public void ExitSession(){
 session.close();
 }

 /* public static void main(String args[]){

 Formation f = new Formation ("124","formation JEE"," module hibernate","03 h ");
 GererFormation agent = new GererFormation();
 agent.AddFormation(f);
 agent.ExitSession();
 }*/
}