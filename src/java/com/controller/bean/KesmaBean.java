/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;
 
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ViewScoped;  
import java.util.List;  
import com.dao.KesmaDAO;  
import com.model.pojo.Kesma;  
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
  
public class KesmaBean implements Serializable  
{  
    private List < Kesma > usersList;  
    private List < Kesma > searchList;  
    private List < Kesma > searchByRecordNoList;  
    KesmaDAO userDao = new KesmaDAO();  
    Kesma user = new Kesma();  
    Kesma newuser = new Kesma();  
    public List < Kesma > getUsers()  
    {  
        usersList = userDao.AllKesmas();  
        int count = usersList.size();  
        return usersList;  
    }  
    public void addKesma()  
    {  
        Integer IdKesma = 0;  
        IdKesma = userDao.getIdKesma();  
        newuser.setIdKesma(IdKesma);  
        String Id = Integer.toString(newuser.getIdKesma());    
        userDao.add(newuser);  
        System.out.println("Suggestion successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User successfully saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        newuser = new Kesma();  
    }  
    public void changeUser(Kesma kesma)  
    {  
        this.user = kesma;  
    }  
    public void UpdateUser(Kesma kesma)  
    {  
        String JudulKesma = kesma.getJudulKesma();  
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "JudulKesma", JudulKesma);  
        RequestContext.getCurrentInstance().showMessageInDialog(message1);  
        userDao.update(user);  
        System.out.println("User Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        user = new Kesma();  
    }  
    public void deleteUser(Kesma user)  
    {  
        String JudulKesma = user.getJudulKesma();          
        userDao.delete(user);  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public void searchbyJudulKesma()  
    {  
        searchByRecordNoList = userDao.SearchByJudulKesma(user.getJudulKesma());  
        int count = searchByRecordNoList.size();  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public Kesma getUser()  
    {  
        return user;  
    }  
    public void setUser(Kesma user)  
    {  
        this.user = user;  
    }  
    public Kesma getNewuser()  
    {  
        return newuser;  
    }  
    public void setNewuser(Kesma newuser)  
    {  
        this.newuser = newuser;  
    }  
    public List < Kesma > getUsersList()  
    {  
        return usersList;  
    }  
    public void setUsersList(List < Kesma > usersList)  
    {  
        this.usersList = usersList;  
    }  
    public List < Kesma > getSearchList()  
    {  
        return searchList;  
    }  
    public void setSearchList(List < Kesma > searchList)  
    {  
        this.searchList = searchList;  
    }  
    public List < Kesma > getSearchByRecordNoList()  
    {  
        return searchByRecordNoList;  
    }  
    public void setSearchByRecordNoList(List < Kesma > searchByRecordNoList)  
    {  
        this.searchByRecordNoList = searchByRecordNoList;  
    }  
   public void onRowEdit(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage(" Edited Record No", ((Kesma) event.getObject()).getJudulKesma());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        Kesma editeduser = (Kesma) event.getObject();  
        userDao.update(editeduser);  
    }  
    public void onCancel(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage("Edit Cancelled");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        usersList.remove((Kesma) event.getObject());  
    }  
} 
