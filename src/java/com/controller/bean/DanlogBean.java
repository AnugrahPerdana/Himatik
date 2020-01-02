/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;
 
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ViewScoped;  
import java.util.List;  
import com.dao.DanlogDAO;  
import com.model.pojo.Danlog;  
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
  
public class DanlogBean implements Serializable  
{  
    private List < Danlog > usersList;  
    private List < Danlog > searchList;  
    private List < Danlog > searchByRecordNoList;  
    DanlogDAO userDao = new DanlogDAO();  
    Danlog user = new Danlog();  
    Danlog newuser = new Danlog();  
    public List < Danlog > getUsers()  
    {  
        usersList = userDao.AllDanlogs();  
        int count = usersList.size();  
        return usersList;  
    }  
    public void addDanlog()  
    {  
        Integer IdDanlog = 0;  
        IdDanlog = userDao.getIdDanlog();  
        newuser.setIdDanlog(IdDanlog);  
        String Id = Integer.toString(newuser.getIdDanlog());    
        userDao.add(newuser);  
        System.out.println("Suggestion successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User successfully saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        newuser = new Danlog();  
    }  
    public void changeUser(Danlog danlog)  
    {  
        this.user = danlog;  
    }  
    public void UpdateUser(Danlog danlog)  
    {  
        String JudulDanlog = danlog.getJudulDanlog();  
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "JudulDanlog", JudulDanlog);  
        RequestContext.getCurrentInstance().showMessageInDialog(message1);  
        userDao.update(user);  
        System.out.println("User Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        user = new Danlog();  
    }  
    public void deleteUser(Danlog user)  
    {  
        String JudulDanlog = user.getJudulDanlog();          
        userDao.delete(user);  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public void searchbyJudulDanlog()  
    {  
        searchByRecordNoList = userDao.SearchByJudulDanlog(user.getJudulDanlog());  
        int count = searchByRecordNoList.size();  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public Danlog getUser()  
    {  
        return user;  
    }  
    public void setUser(Danlog user)  
    {  
        this.user = user;  
    }  
    public Danlog getNewuser()  
    {  
        return newuser;  
    }  
    public void setNewuser(Danlog newuser)  
    {  
        this.newuser = newuser;  
    }  
    public List < Danlog > getUsersList()  
    {  
        return usersList;  
    }  
    public void setUsersList(List < Danlog > usersList)  
    {  
        this.usersList = usersList;  
    }  
    public List < Danlog > getSearchList()  
    {  
        return searchList;  
    }  
    public void setSearchList(List < Danlog > searchList)  
    {  
        this.searchList = searchList;  
    }  
    public List < Danlog > getSearchByRecordNoList()  
    {  
        return searchByRecordNoList;  
    }  
    public void setSearchByRecordNoList(List < Danlog > searchByRecordNoList)  
    {  
        this.searchByRecordNoList = searchByRecordNoList;  
    }  
   public void onRowEdit(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage(" Edited Record No", ((Danlog) event.getObject()).getJudulDanlog());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        Danlog editeduser = (Danlog) event.getObject();  
        userDao.update(editeduser);  
    }  
    public void onCancel(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage("Edit Cancelled");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        usersList.remove((Danlog) event.getObject());  
    }  
} 
