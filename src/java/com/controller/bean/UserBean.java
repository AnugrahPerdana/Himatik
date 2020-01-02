/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.bean;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import com.dao.UserDAO;
import com.model.pojo.*;
import java.io.Serializable;

/**
 *
 * @author user
 */
@ManagedBean
@ViewScoped
public class UserBean implements Serializable{

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
    }
    private List < User > usersList;
    UserDAO userDao = new UserDAO();  
    User user = new User();  
    User newuser = new User();  

    public List<User> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<User> usersList) {
        this.usersList = usersList;
    }

    public UserDAO getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDAO userDao) {
        this.userDao = userDao;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getNewuser() {
        return newuser;
    }

    public void setNewuser(User newuser) {
        this.newuser = newuser;
    }
    
    public void adduser()  
    {   
        int userId;
        String role;
        userId = userDao.getId();  
        newuser.setId(userId);  
        String Id = Integer.toString(newuser.getId());  
        userDao.add(newuser);  
        System.out.println("User successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User successfully saved.");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        newuser = new User();  
    }  
    public void changeTbluser(User user)  
    {  
        this.user = user;  
    }  
    public void UpdateTbluser(User user)  
    {  
        String username = user.getUsername();  
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Name", username);  
        RequestContext.getCurrentInstance().showMessageInDialog(message1);  
        userDao.update(user);  
        System.out.println("User Info successfully saved.");  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "User updated successfully .");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
        user = new User();  
    }  
    public void deleteUser(User user)  
    {  
        String username = user.getUsername();          
        userDao.delete(user);  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");  
        RequestContext.getCurrentInstance().showMessageInDialog(message);  
    }
    public List < User > getUsers()  
    {  
        usersList = userDao.Login();  
        int count = usersList.size();  
        return usersList;  
    }  
}
