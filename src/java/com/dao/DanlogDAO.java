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
import com.model.pojo.Danlog;  
import javax.faces.application.FacesMessage;  
import org.primefaces.context.RequestContext;  
/**
 *
 * @author FACULTY
 */
public class DanlogDAO  
{  
    private Danlog danlog;  
    private Danlog newdanlog;  
    private List < Danlog > DaoAllDanlog;  
    private List < Danlog > DaoSearchDanlogList;  
    //Session session;  
    public List < Danlog > AllDanlogs()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllDanlog = session.createCriteria(Danlog.class).list();  
            int count = DaoAllDanlog.size();  
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
        return DaoAllDanlog;  
    }  
    public Integer getIdDanlog()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.idDanlog) from Danlog U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer IdDanlog = 1;  
        if (results.get(0) != null)  
        {  
            IdDanlog = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return IdDanlog;  
    }  
    public List < Danlog > SearchByJudulDanlog(String JudulDanlog)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < Danlog > daoSearchList = new ArrayList < > ();  
        try  
        {  
            session.beginTransaction();  
            Query qu = session.createQuery("From Danlog U where U.judulDanlog =:judulDanlog"); //User is the entity not the table  
            qu.setParameter("judulDanlog", JudulDanlog);  
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
    public void add(Danlog newdanlog)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Id = Integer.toString(newdanlog.getIdDanlog());  
            // begin a transaction  
            session.beginTransaction();  
            session.merge(newdanlog);  
            session.flush();  
            System.out.println("NewDanlog saved, id: " +  
                newdanlog.getIdDanlog());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(Danlog danlog)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String judulDanlog = danlog.getJudulDanlog();  
            session.beginTransaction();  
            session.delete(danlog);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(Danlog danlog)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(danlog);  
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
