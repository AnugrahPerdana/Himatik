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
import com.model.pojo.Keroh;  
import javax.faces.application.FacesMessage;  
import org.primefaces.context.RequestContext;  
/**
 *
 * @author FACULTY
 */
public class KerohDAO  
{  
    private Keroh keroh;  
    private Keroh newkeroh;  
    private List < Keroh > DaoAllKeroh;  
    private List < Keroh > DaoSearchKerohList;  
    //Session session;  
    public List < Keroh > AllKerohs()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllKeroh = session.createCriteria(Keroh.class).list();  
            int count = DaoAllKeroh.size();  
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
        return DaoAllKeroh;  
    }  
    public Integer getIdKeroh()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.idKeroh) from Keroh U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer IdKeroh = 1;  
        if (results.get(0) != null)  
        {  
            IdKeroh = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return IdKeroh;  
    }  
    public List < Keroh > SearchByJudulKeroh(String JudulKeroh)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < Keroh > daoSearchList = new ArrayList < > ();  
        try  
        {  
            session.beginTransaction();  
            Query qu = session.createQuery("From Keroh U where U.judulKeroh =:judulKeroh"); //User is the entity not the table  
            qu.setParameter("judulKeroh", JudulKeroh);  
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
    public void add(Keroh newkeroh)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Id = Integer.toString(newkeroh.getIdKeroh());  
            // begin a transaction  
            session.beginTransaction();  
            session.merge(newkeroh);  
            session.flush();  
            System.out.println("NewKeroh saved, id: " +  
                newkeroh.getIdKeroh());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(Keroh keroh)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String judulKeroh = keroh.getJudulKeroh();  
            session.beginTransaction();  
            session.delete(keroh);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(Keroh keroh)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(keroh);  
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
