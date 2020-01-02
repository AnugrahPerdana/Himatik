/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;
 
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ViewScoped;  
import java.util.List;  
import com.dao.DaftarDAO;  
import com.model.pojo.Daftar;  
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
  
public class DaftarBean implements Serializable  
{  
    private List < Daftar > usersList;  
    private List < Daftar > searchList;  
    private List < Daftar > searchByRecordNoList;  
    DaftarDAO userDao = new DaftarDAO();  
    Daftar user = new Daftar();  
    Daftar newuser = new Daftar();  
    public List < Daftar > getUsers()  
    {  
        usersList = userDao.AllDaftars();  
        int count = usersList.size();  
        return usersList;  
    }  
    public void addDaftar()  
    {  
        Integer IdDaftar = 0;  
        IdDaftar = userDao.getIdDaftar();  
        newuser.setIdDaftar(IdDaftar);  
        String Id = Integer.toString(newuser.getIdDaftar());    
        userDao.add(newuser);  
        System.out.println("Suggestion successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User successfully saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        newuser = new Daftar();  
    }  
    public void changeUser(Daftar daftar)  
    {  
        this.user = daftar;  
    }  
    public void UpdateUser(Daftar daftar)  
    {  
        String NamaDaftar = daftar.getNamaDaftar();  
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "NamaDaftar", NamaDaftar);  
        RequestContext.getCurrentInstance().showMessageInDialog(message1);  
        userDao.update(user);  
        System.out.println("User Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        user = new Daftar();  
    }  
    public void deleteUser(Daftar user)  
    {  
        String Nama_Daftar = user.getNamaDaftar();          
        userDao.delete(user);  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public void searchbyNamaDaftar()  
    {  
        searchByRecordNoList = userDao.SearchByNamaDaftar(user.getNamaDaftar());  
        int count = searchByRecordNoList.size();  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Number of Record Selected:", Integer.toString(count));  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }  
    public Daftar getUser()  
    {  
        return user;  
    }  
    public void setUser(Daftar user)  
    {  
        this.user = user;  
    }  
    public Daftar getNewuser()  
    {  
        return newuser;  
    }  
    public void setNewuser(Daftar newuser)  
    {  
        this.newuser = newuser;  
    }  
    public List < Daftar > getUsersList()  
    {  
        return usersList;  
    }  
    public void setUsersList(List < Daftar > usersList)  
    {  
        this.usersList = usersList;  
    }  
    public List < Daftar > getSearchList()  
    {  
        return searchList;  
    }  
    public void setSearchList(List < Daftar > searchList)  
    {  
        this.searchList = searchList;  
    }  
    public List < Daftar > getSearchByRecordNoList()  
    {  
        return searchByRecordNoList;  
    }  
    public void setSearchByRecordNoList(List < Daftar > searchByRecordNoList)  
    {  
        this.searchByRecordNoList = searchByRecordNoList;  
    }  
   public void onRowEdit(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage(" Edited Record No", ((Daftar) event.getObject()).getNamaDaftar());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        Daftar editeduser = (Daftar) event.getObject();  
        userDao.update(editeduser);  
    }  
    public void onCancel(RowEditEvent event)  
    {  
        FacesMessage msg = new FacesMessage("Edit Cancelled");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        usersList.remove((Daftar) event.getObject());  
    }  
} 
