/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;
import java.util.List;  
import java.util.ArrayList;  
import org.hibernate.Query;  
import org.hibernate.Session;  
import com.util.HibernateUtil;  
import com.model.pojo.Sosma;  
import javax.faces.application.FacesMessage;  
import org.primefaces.context.RequestContext;  
/**
 *
 * @author FACULTY
 */
public class SosmaDAO  
{  
    private Sosma sosma;  
    private Sosma newsosma;  
    private List < Sosma > DaoAllSosma;  
    private List < Sosma > DaoSearchSosmaList;  
    //Session session;  
    public List < Sosma > AllSosmas()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllSosma = session.createCriteria(Sosma.class).list();  
            int count = DaoAllSosma.size();  
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
        return DaoAllSosma;  
    }  
    public Integer getIdSosma()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.idSosma) from Sosma U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer IdSosma = 1;  
        if (results.get(0) != null)  
        {  
            IdSosma = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return IdSosma;  
    }  
    public List < Sosma > SearchByJudulSosma(String JudulSosma)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < Sosma > daoSearchList = new ArrayList < > ();  
        try  
        {  
            session.beginTransaction();  
            Query qu = session.createQuery("From Sosma U where U.judulSosma =:judulSosma"); //User is the entity not the table  
            qu.setParameter("judulSosma", JudulSosma);  
            daoSearchList = qu.list();  
            int count = daoSearchList.size();  
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
    public void add(Sosma newsosma)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Id = Integer.toString(newsosma.getIdSosma());  
            // begin a transaction  
            session.beginTransaction();  
            session.merge(newsosma);  
            session.flush();  
            System.out.println("NewSosma saved, id: " +  
                newsosma.getIdSosma());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(Sosma sosma)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String judulSosma = sosma.getJudulSosma();  
            session.beginTransaction();  
            session.delete(sosma);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(Sosma sosma)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(sosma);  
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
} 
