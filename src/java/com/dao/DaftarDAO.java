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
import com.model.pojo.Daftar;  
import javax.faces.application.FacesMessage;  
import org.primefaces.context.RequestContext;  
/**
 *
 * @author FACULTY
 */
public class DaftarDAO  
{  
    private Daftar daftar;  
    private Daftar newdaftar;  
    private List < Daftar > DaoAllDaftar;  
    private List < Daftar > DaoSearchDaftarList;  
    //Session session;  
    public List < Daftar > AllDaftars()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllDaftar = session.createCriteria(Daftar.class).list();  
            int count = DaoAllDaftar.size();  
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
        return DaoAllDaftar;  
    }  
    public Integer getIdDaftar()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.idDaftar) from Daftar U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer IdDaftar = 1;  
        if (results.get(0) != null)  
        {  
            IdDaftar = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return IdDaftar;  
    }  
    public List < Daftar > SearchByNamaDaftar(String NamaDaftar)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < Daftar > daoSearchList = new ArrayList < > ();  
        try  
        {  
            session.beginTransaction();  
            Query qu = session.createQuery("From Daftar U where U.namaDaftar =:namaDaftar"); //User is the entity not the table  
            qu.setParameter("namaDaftar", NamaDaftar);  
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
    public void add(Daftar newdaftar)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Id = Integer.toString(newdaftar.getIdDaftar());  
            // begin a transaction  
            session.beginTransaction();  
            session.merge(newdaftar);  
            session.flush();  
            System.out.println("NewDaftar saved, id: " +  
                newdaftar.getIdDaftar());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(Daftar daftar)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String nama_daftar = daftar.getNamaDaftar();  
            session.beginTransaction();  
            session.delete(daftar);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(Daftar daftar)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(daftar);  
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
