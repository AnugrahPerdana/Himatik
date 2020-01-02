/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import com.model.pojo.User;
import java.util.ArrayList;
//import function.login;
import org.hibernate.Transaction;
/**
 *
 * @author user
 */
public class UserDAO {
     private User user;  
    private User newuser; 
    private List < User > DaoAllUsers;  
    private List < User > DaoSearchUserList;  
    //Session session;  
    public List < User > AllUsers()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllUsers = session.createCriteria(User.class).list();  
            int count = DaoAllUsers.size();  
            // FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "List Size", Integer.toString(count));//Debugging Purpose  
            //RequestContext.getCurrentInstance().showMessageInDialog(message1);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
        return DaoAllUsers;  
    }  
    
     public Integer getId()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.id) from user U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer userId = 1;  
        if (results.get(0) != null)  
        {  
            userId = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return userId;  
    }  
     
     public void add(User newuser)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Id = Integer.toString(newuser.getId());  
            // begin a transaction  
            session.beginTransaction();  
            session.merge(newuser);  
            session.flush();  
            System.out.println("NewUser saved, id: " +  
                newuser.getId());  
            session.getTransaction().commit();
            String message = "NewUser saved, id: " +  
                newuser.getId();
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(User user)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String username = user.getUsername();  
            session.beginTransaction();  
            session.delete(user);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(User user)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(user);  
            session.flush();  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getNewuser() {
        return newuser;
    }

    public void setNewuser(User newuser) {
        this.newuser = newuser;
    }

    public List<User> getDaoAllUsers() {
        return DaoAllUsers;
    }

    public void setDaoAllUsers(List<User> DaoAllUsers) {
        this.DaoAllUsers = DaoAllUsers;
    }

    public List<User> getDaoSearchUserList() {
        return DaoSearchUserList;
    }

    public void setDaoSearchUserList(List<User> DaoSearchUserList) {
        this.DaoSearchUserList = DaoSearchUserList;
    }
 
    public List < User > Login()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < User > daoSearchList = new ArrayList < > ();  
        try  
        {  
            session.beginTransaction();  
            Query qu = session.createQuery("From User U where U.username =:Username"); //User is the entity not the table  
            qu.setParameter("username", getId());  
            daoSearchList = qu.list();
            int count = daoSearchList.size();
            System.out.println(daoSearchList);
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        finally  
        {  
            session.close();  
        }  
        return daoSearchList;  
    }  
    
     public boolean validateLogin(User user){
        Transaction transObj = null;
        Session sessionObj = HibernateUtil.getSessionFactory().openSession();
        List<User> listakun = new ArrayList<User>();
        try{
            transObj = sessionObj.beginTransaction();
            Query query = sessionObj.createQuery("from User where  username= :username and password = :password");
            query.setParameter("username", user.getUsername());
            query.setParameter("password", user.getPassword());
            listakun = query.list();
            
            transObj.commit();
            System.out.println("Sukses");
        }catch(Exception ex){
            ex.printStackTrace();
            transObj.rollback();
        }finally{
            sessionObj.flush();
            sessionObj.close();
        }
        if(listakun.size()>0){
            return true;
        }else{
            return false;
        }
    }
}
