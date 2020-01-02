/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;
 
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ViewScoped;  
import java.util.List;  
import com.dao.ScheduleDAO;  
import com.model.pojo.Schedule;  
import java.io.Serializable;  
import org.primefaces.event.RowEditEvent;  
import javax.faces.context.FacesContext;  
import javax.faces.application.FacesMessage;  
import org.primefaces.context.RequestContext; 

/**
 *
 * @author FACULTY
 */
@ManagedBean
@ViewScoped
  
public class ScheduleBean implements Serializable  
{  
    private List < Schedule > usersList;  
    private List < Schedule > searchList;  
    private List < Schedule > searchByRecordNoList;  
    ScheduleDAO userDao = new ScheduleDAO();  
    Schedule user = new Schedule();  
    Schedule newuser = new Schedule();  
    public List < Schedule > getUsers()  
    {  
        usersList = userDao.AllSchedules();  
        int count = usersList.size();  
        return usersList;  
    }  
    public void addSchedule()  
    {  
        String NamaAcara = newuser.getNamaAcara();  
        Integer IdSchedule = 0;  
        IdSchedule = userDao.getIdSchedule();  
        newuser.setIdSchedule(IdSchedule);  
       String Id = Integer.toString(newuser.getIdSchedule());    
        userDao.add(newuser);  
        System.out.println("Schedule successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User successfully saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        newuser = new Schedule();  
    }  
    public void changeUser(Schedule schedule)  
    {  
        this.user = schedule;  
    }  
    public void UpdateUser(Schedule schedule)  
    {  
        String NamaAcara = schedule.getNamaAcara();  
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "NamaAcara", NamaAcara);  
        RequestContext.getCurrentInstance().showMessageInDialog(message1);  
        userDao.update(user);  
        System.out.println("User Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        user = new Schedule();  
    }  
    public void deleteUser(Schedule user)  
    {  
        String NamaAcara = user.getNamaAcara();          
        userDao.delete(user);  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public void searchbyNamaAcara()  
    {  
        searchByRecordNoList = userDao.SearchByNamaAcara(user.getNamaAcara());  
        int count = searchByRecordNoList.size();  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public Schedule getUser()  
    {  
        return user;  
    }  
    public void setUser(Schedule user)  
    {  
        this.user = user;  
    }  
    public Schedule getNewuser()  
    {  
        return newuser;  
    }  
    public void setNewuser(Schedule newuser)  
    {  
        this.newuser = newuser;  
    }  
    public List < Schedule > getUsersList()  
    {  
        return usersList;  
    }  
    public void setUsersList(List < Schedule > usersList)  
    {  
        this.usersList = usersList;  
    }  
    public List < Schedule > getSearchList()  
    {  
        return searchList;  
    }  
    public void setSearchList(List < Schedule > searchList)  
    {  
        this.searchList = searchList;  
    }  
    public List < Schedule > getSearchByRecordNoList()  
    {  
        return searchByRecordNoList;  
    }  
    public void setSearchByRecordNoList(List < Schedule > searchByRecordNoList)  
    {  
        this.searchByRecordNoList = searchByRecordNoList;  
    }  
   public void onRowEdit(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage(" Edited Record No", ((Schedule) event.getObject()).getNamaAcara());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        Schedule editeduser = (Schedule) event.getObject();  
        userDao.update(editeduser);  
    }  
    public void onCancel(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage("Edit Cancelled");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        usersList.remove((Schedule) event.getObject());  
    }    
} 
