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
import com.model.pojo.Kesma;  
import javax.faces.application.FacesMessage;  
import org.primefaces.context.RequestContext;  
/**
 *
 * @author FACULTY
 */
public class KesmaDAO  
{  
    private Kesma kesma;  
    private Kesma newkesma;  
    private List < Kesma > DaoAllKesma;  
    private List < Kesma > DaoSearchKesmaList;  
    //Session session;  
    public List < Kesma > AllKesmas()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllKesma = session.createCriteria(Kesma.class).list();  
            int count = DaoAllKesma.size();  
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
        return DaoAllKesma;  
    }  
    public Integer getIdKesma()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.idKesma) from Kesma U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer IdKesma = 1;  
        if (results.get(0) != null)  
        {  
            IdKesma = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return IdKesma;  
    }  
    public List < Kesma > SearchByJudulKesma(String JudulKesma)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < Kesma > daoSearchList = new ArrayList < > ();  
        try  
        {  
            session.beginTransaction();  
            Query qu = session.createQuery("From Kesma U where U.judulKesma =:judulKesma"); //User is the entity not the table  
            qu.setParameter("judulKesma", JudulKesma);  
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
    public void add(Kesma newkesma)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Id = Integer.toString(newkesma.getIdKesma());  
            // begin a transaction  
            session.beginTransaction();  
            session.merge(newkesma);  
            session.flush();  
            System.out.println("NewKesma saved, id: " +  
                newkesma.getIdKesma());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(Kesma kesma)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String judulKesma = kesma.getJudulKesma();  
            session.beginTransaction();  
            session.delete(kesma);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(Kesma kesma)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(kesma);  
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
