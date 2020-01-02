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
import com.model.pojo.Schedule;
import javax.faces.application.FacesMessage;  
import org.primefaces.context.RequestContext;  
/**
 *
 * @author FACULTY
 */
public class ScheduleDAO  
{  
    private Schedule schedule;  
    private Schedule newschedule;  
    private List < Schedule > DaoAllSchedule;  
    private List < Schedule > DaoSearchScheduleList;  
    //Session session;  
    public List < Schedule > AllSchedules()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllSchedule = session.createCriteria(Schedule.class).list();  
            int count = DaoAllSchedule.size();  
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
        return DaoAllSchedule;  
    }  
    public Integer getIdSchedule()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.idSchedule) from Schedule U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer IdSchedule = 1;  
        if (results.get(0) != null)  
        {  
            IdSchedule = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return IdSchedule;  
    }  
    public List < Schedule > SearchByNamaAcara(String NamaAcara)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        List < Schedule > daoSearchList = new ArrayList < > ();  
        try  
        {  
            session.beginTransaction();  
            Query qu = session.createQuery("From Schedule U where U.namaAcara =:namaAcara"); //User is the entity not the table  
            qu.setParameter("namaAcara", NamaAcara);  
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
    public void add(Schedule newschedule)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String Id = Integer.toString(newschedule.getIdSchedule());  
            // begin a transaction  
            session.beginTransaction();  
            session.merge(newschedule);  
            session.flush();  
            System.out.println("NewSchedule saved, id: " +  
                newschedule.getIdSchedule());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(Schedule schedule)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String NamaAcara = schedule.getNamaAcara();  
            session.beginTransaction();  
            session.delete(schedule);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(Schedule schedule)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(schedule);  
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
