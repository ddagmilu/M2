package org.example;

import java.awt.*;

public class User {
    private int idUser;
    private String Name;
    private String Username;
    private String Password;
    private int Privilege;
    private Image Userimage;

    public User(int idUser, String name, String username, String password, int privilege, Image userimage) {
        this.idUser = idUser;
        Name = name;
        Username = username;
        Password = password;
        Privilege = privilege;
        Userimage = userimage;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getPrivilege() {
        return Privilege;
    }

    public void setPrivilege(int privilege) {
        Privilege = privilege;
    }

    public Image getUserimage() {
        return Userimage;
    }

    public void setUserimage(Image userimage) {
        Userimage = userimage;
    }
}
