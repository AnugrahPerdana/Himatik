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
import com.model.pojo.Sospol;  
import javax.faces.application.FacesMessage;  
import org.primefaces.context.RequestContext;  
/**
 *
 * @author FACULTY
 */
public class SospolDAO  
{  
    private Sospol sospol;  
    private Sospol newsospol;  
    private List < Sospol > DaoAllSospol;  
    private List < Sospol > DaoSearchSospolList;  
    //Session session;  
    public List < Sospol > AllSospols()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllSospol = session.createCriteria(Sospol.class).list();  
            int count = DaoAllSospol.size();  
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
        return DaoAllSospol;  
    }  
    public Integer getIdSospol()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.idSospol) from Sospol U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer IdSospol = 1;  
        if (results.get(0) != null)  
        {  
            IdSospol = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return IdSospol;  
    }  
    public List < Sospol > SearchByJudulSospol(String JudulSospol)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < Sospol > daoSearchList = new ArrayList < > ();  
        try  
        {  
            session.beginTransaction();  
            Query qu = session.createQuery("From Sospol U where U.judulSospol =:judulSospol"); //User is the entity not the table  
            qu.setParameter("judulSospol", JudulSospol);  
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
    public void add(Sospol newsospol)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Id = Integer.toString(newsospol.getIdSospol());  
            // begin a transaction  
            session.beginTransaction();  
            session.merge(newsospol);  
            session.flush();  
            System.out.println("Newsospol saved, id: " +  
                newsospol.getIdSospol());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(Sospol sospol)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String judulSospol = sospol.getJudulSospol();  
            session.beginTransaction();  
            session.delete(sospol);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(Sospol sospol)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(sospol);  
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
