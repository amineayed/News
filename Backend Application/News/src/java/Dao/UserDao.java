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
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Firass
 */
public class UserDao {
    private NewHibernateUtil helper;
 private Session session;
 
    /**
     *
     */
    public UserDao() {
 helper = new NewHibernateUtil();
 session = helper.getSessionFactory().openSession();
 }

    /**
     *
     * @param f
     */
    public User AddUser(User f) {

 session.beginTransaction();
 session.save(f);
 
         session.getTransaction().commit();
         User user=null;
                 user=this.FindUser(f.getIduser());
         return user;

    }
    
    public void addUser_Prefences(int id,String category){
        session.createSQLQuery("insert into user_preferences values("+id+",\"category\")").executeUpdate();
    }

    /**
     *
     * @param f
     */
    public void EditUser(User f) {
 session.beginTransaction();
 session.update(f);
 session.getTransaction().commit();
 }

    /**
     *
     * @param id
     * @return
     */
    public User FindUser(int id) {
 session.beginTransaction();
 User f=null;
 f = (User) session.get(User.class, id);
 return (f);
 }
 
    /**
     *
     * @param login
     * @param password
     * @return
     */
    public User GetUser(String login,String password){
     User user =null;
        Query query =session.createQuery("select u from User u where login = :login and password = :password");
        query.setParameter("login", login);
        query.setParameter("password",password);
        user=(User) query.uniqueResult();
     return user;
 }

    /**
     *
     * @param id
     */
    public void RemoveUser(int id) {
 session.beginTransaction();
 User f;
 f = FindUser(id);
 session.delete(f);
 session.getTransaction().commit();
 }

    /**
     *
     * @return
     */
    public List<Category> FindAll(){

 return session.createSQLQuery("select u from User u ").list();
 }

    /**
     *
     */
    public void ExitSession(){
 session.close();
 }

    /**
     *
     * @param mail
     * @return
     */
    public List<User> findbyMail(String mail){
     return session.createQuery("select u from User u where mail like '%:mail%'").setParameter("mail", mail).list();
 }

 public String fromListToJson(Set CategorySet){
                
                
                String json="";
                  
                json=json+"[";
             
Category category=null;
                Object[] CategoriesArray = CategorySet.toArray(new Category[CategorySet.size()]);
                
for (int i = 0; i < CategoriesArray.length-2; i++) {
    category=(Category)CategoriesArray[i];
 json=json+(category.toString())+",";
}

               json=json+CategoriesArray[CategoriesArray.length-1].toString()+"]";
                return json;
            }

}
