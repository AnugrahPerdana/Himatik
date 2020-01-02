/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;
 
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ViewScoped;  
import java.util.List;  
import com.dao.SospolDAO;  
import com.model.pojo.Sospol;  
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
  
public class SospolBean implements Serializable  
{  
    private List < Sospol > usersList;  
    private List < Sospol > searchList;  
    private List < Sospol > searchByRecordNoList;  
    SospolDAO userDao = new SospolDAO();  
    Sospol user = new Sospol();  
    Sospol newuser = new Sospol();  
    public List < Sospol > getUsers()  
    {  
        usersList = userDao.AllSospols();  
        int count = usersList.size();  
        return usersList;  
    }  
    public void addSospol()  
    {  
        Integer IdSospol = 0;  
        IdSospol = userDao.getIdSospol();  
        newuser.setIdSospol(IdSospol);  
        String Id = Integer.toString(newuser.getIdSospol());    
        userDao.add(newuser);  
        System.out.println("Suggestion successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User successfully saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        newuser = new Sospol();  
    }  
    public void changeUser(Sospol sospol)  
    {  
        this.user = sospol;  
    }  
    public void UpdateUser(Sospol sospol)  
    {  
        String JudulSospol = sospol.getJudulSospol();  
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "JudulSospol", JudulSospol);  
        RequestContext.getCurrentInstance().showMessageInDialog(message1);  
        userDao.update(user);  
        System.out.println("User Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        user = new Sospol();  
    }  
    public void deleteUser(Sospol user)  
    {  
        String JudulSospol = user.getJudulSospol();          
        userDao.delete(user);  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public void searchbyJudulSospol()  
    {  
        searchByRecordNoList = userDao.SearchByJudulSospol(user.getJudulSospol());  
        int count = searchByRecordNoList.size();  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public Sospol getUser()  
    {  
        return user;  
    }  
    public void setUser(Sospol user)  
    {  
        this.user = user;  
    }  
    public Sospol getNewuser()  
    {  
        return newuser;  
    }  
    public void setNewuser(Sospol newuser)  
    {  
        this.newuser = newuser;  
    }  
    public List < Sospol > getUsersList()  
    {  
        return usersList;  
    }  
    public void setUsersList(List < Sospol > usersList)  
    {  
        this.usersList = usersList;  
    }  
    public List < Sospol > getSearchList()  
    {  
        return searchList;  
    }  
    public void setSearchList(List < Sospol > searchList)  
    {  
        this.searchList = searchList;  
    }  
    public List < Sospol > getSearchByRecordNoList()  
    {  
        return searchByRecordNoList;  
    }  
    public void setSearchByRecordNoList(List < Sospol > searchByRecordNoList)  
    {  
        this.searchByRecordNoList = searchByRecordNoList;  
    }  
   public void onRowEdit(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage(" Edited Record No", ((Sospol) event.getObject()).getJudulSospol());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        Sospol editeduser = (Sospol) event.getObject();  
        userDao.update(editeduser);  
    }  
    public void onCancel(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage("Edit Cancelled");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        usersList.remove((Sospol) event.getObject());  
    }  
} 
