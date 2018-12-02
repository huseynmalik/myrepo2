/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package az.orient.course.model;

import java.util.Date;

/**
 *
 * @author Ideas.az
 */
public class Login extends CourseModel{
    
    private String username;
    private String password;
    private Role   role;
    private String name;
    private String surname;
    private Date   loginDate;
    private String token;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Login{" + "username=" + username + ", password=" + password + ", role=" + role + ", name=" + name + ", surname=" + surname + ", loginDate=" + loginDate + '}';
    }
    
    
    
    
    
}
