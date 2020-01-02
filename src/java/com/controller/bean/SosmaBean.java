/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;
 
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ViewScoped;  
import java.util.List;  
import com.dao.SosmaDAO;  
import com.model.pojo.Sosma;  
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
  
public class SosmaBean implements Serializable  
{  
    private List < Sosma > usersList;  
    private List < Sosma > searchList;  
    private List < Sosma > searchByRecordNoList;  
    SosmaDAO userDao = new SosmaDAO();  
    Sosma user = new Sosma();  
    Sosma newuser = new Sosma();  
    public List < Sosma > getUsers()  
    {  
        usersList = userDao.AllSosmas();  
        int count = usersList.size();  
        return usersList;  
    }  
    public void addSosma()  
    {  
        Integer IdSosma = 0;  
        IdSosma = userDao.getIdSosma();  
        newuser.setIdSosma(IdSosma);  
        String Id = Integer.toString(newuser.getIdSosma());    
        userDao.add(newuser);  
        System.out.println("Suggestion successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User successfully saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        newuser = new Sosma();  
    }  
    public void changeUser(Sosma sosma)  
    {  
        this.user = sosma;  
    }  
    public void UpdateUser(Sosma sosma)  
    {  
        String JudulSosma = sosma.getJudulSosma();  
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "JudulSosma", JudulSosma);  
        RequestContext.getCurrentInstance().showMessageInDialog(message1);  
        userDao.update(user);  
        System.out.println("User Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        user = new Sosma();  
    }  
    public void deleteUser(Sosma user)  
    {  
        String JudulSosma = user.getJudulSosma();          
        userDao.delete(user);  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public void searchbyJudulSosma()  
    {  
        searchByRecordNoList = userDao.SearchByJudulSosma(user.getJudulSosma());  
        int count = searchByRecordNoList.size();  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public Sosma getUser()  
    {  
        return user;  
    }  
    public void setUser(Sosma user)  
    {  
        this.user = user;  
    }  
    public Sosma getNewuser()  
    {  
        return newuser;  
    }  
    public void setNewuser(Sosma newuser)  
    {  
        this.newuser = newuser;  
    }  
    public List < Sosma > getUsersList()  
    {  
        return usersList;  
    }  
    public void setUsersList(List < Sosma > usersList)  
    {  
        this.usersList = usersList;  
    }  
    public List < Sosma > getSearchList()  
    {  
        return searchList;  
    }  
    public void setSearchList(List < Sosma > searchList)  
    {  
        this.searchList = searchList;  
    }  
    public List < Sosma > getSearchByRecordNoList()  
    {  
        return searchByRecordNoList;  
    }  
    public void setSearchByRecordNoList(List < Sosma > searchByRecordNoList)  
    {  
        this.searchByRecordNoList = searchByRecordNoList;  
    }  
   public void onRowEdit(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage(" Edited Record No", ((Sosma) event.getObject()).getJudulSosma());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        Sosma editeduser = (Sosma) event.getObject();  
        userDao.update(editeduser);  
    }  
    public void onCancel(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage("Edit Cancelled");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        usersList.remove((Sosma) event.getObject());  
    }  
} 
