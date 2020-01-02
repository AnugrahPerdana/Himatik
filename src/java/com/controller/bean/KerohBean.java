/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;
 
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ViewScoped;  
import java.util.List;  
import com.dao.KerohDAO;
import com.model.pojo.Keroh;  
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
  
public class KerohBean implements Serializable  
{  
    private List < Keroh > usersList;  
    private List < Keroh > searchList;  
    private List < Keroh > searchByRecordNoList;  
    KerohDAO userDao = new KerohDAO();  
    Keroh user = new Keroh();  
    Keroh newuser = new Keroh();  
    public List < Keroh > getUsers()  
    {  
        usersList = userDao.AllKerohs();  
        int count = usersList.size();  
        return usersList;  
    }  
    public void addKeroh()  
    {  
        Integer IdKeroh = 0;  
        IdKeroh = userDao.getIdKeroh();  
        newuser.setIdKeroh(IdKeroh);  
        String Id = Integer.toString(newuser.getIdKeroh());    
        userDao.add(newuser);  
        System.out.println("Suggestion successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User successfully saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        newuser = new Keroh();  
    }  
    public void changeUser(Keroh keroh)  
    {  
        this.user = keroh;  
    }  
    public void UpdateUser(Keroh keroh)  
    {  
        String JudulKeroh = keroh.getJudulKeroh();  
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "JudulKeroh", JudulKeroh);  
        RequestContext.getCurrentInstance().showMessageInDialog(message1);  
        userDao.update(user);  
        System.out.println("User Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        user = new Keroh();  
    }  
    public void deleteUser(Keroh user)  
    {  
        String JudulKeroh = user.getJudulKeroh();          
        userDao.delete(user);  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public void searchbyJudulKeroh()  
    {  
        searchByRecordNoList = userDao.SearchByJudulKeroh(user.getJudulKeroh());  
        int count = searchByRecordNoList.size();  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public Keroh getUser()  
    {  
        return user;  
    }  
    public void setUser(Keroh user)  
    {  
        this.user = user;  
    }  
    public Keroh getNewuser()  
    {  
        return newuser;  
    }  
    public void setNewuser(Keroh newuser)  
    {  
        this.newuser = newuser;  
    }  
    public List < Keroh > getUsersList()  
    {  
        return usersList;  
    }  
    public void setUsersList(List < Keroh > usersList)  
    {  
        this.usersList = usersList;  
    }  
    public List < Keroh > getSearchList()  
    {  
        return searchList;  
    }  
    public void setSearchList(List < Keroh > searchList)  
    {  
        this.searchList = searchList;  
    }  
    public List < Keroh > getSearchByRecordNoList()  
    {  
        return searchByRecordNoList;  
    }  
    public void setSearchByRecordNoList(List < Keroh > searchByRecordNoList)  
    {  
        this.searchByRecordNoList = searchByRecordNoList;  
    }  
   public void onRowEdit(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage(" Edited Record No", ((Keroh) event.getObject()).getJudulKeroh());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        Keroh editeduser = (Keroh) event.getObject();  
        userDao.update(editeduser);  
    }  
    public void onCancel(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage("Edit Cancelled");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        usersList.remove((Keroh) event.getObject());  
    }  
} 
