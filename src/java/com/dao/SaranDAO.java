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
import com.model.pojo.Saran;  
import javax.faces.application.FacesMessage;  
import org.primefaces.context.RequestContext;  
/**
 *
 * @author FACULTY
 */
public class SaranDAO  
{  
    private Saran saran;  
    private Saran newsaran;  
    private List < Saran > DaoAllSaran;  
    private List < Saran > DaoSearchSaranList;  
    //Session session;  
    public List < Saran > AllSarans()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllSaran = session.createCriteria(Saran.class).list();  
            int count = DaoAllSaran.size();  
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
        return DaoAllSaran;  
    }  
    public Integer getIdSaran()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.idSaran) from Saran U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer IdSaran = 1;  
        if (results.get(0) != null)  
        {  
            IdSaran = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return IdSaran;  
    }  
    public List < Saran > SearchByNama(String Nama)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < Saran > daoSearchList = new ArrayList < > ();  
        try  
        {  
            session.beginTransaction();  
            Query qu = session.createQuery("From Saran U where U.nama =:nama"); //User is the entity not the table  
            qu.setParameter("nama", Nama);  
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
    public void add(Saran newsaran)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Id = Integer.toString(newsaran.getIdSaran());  
            // begin a transaction  
            session.beginTransaction();  
            session.merge(newsaran);  
            session.flush();  
            System.out.println("NewSaran saved, id: " +  
                newsaran.getIdSaran());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(Saran saran)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String nama = saran.getNama();  
            session.beginTransaction();  
            session.delete(saran);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(Saran saran)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(saran);  
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
