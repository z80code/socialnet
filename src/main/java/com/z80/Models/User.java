package com.z80.Models;


import com.z80.services.Security;

// POJO
public class User {

    private int id;
    private String name;
    private String password;
    private String hashpass;
    private String hashsession;
    private String hashcookie;
    private String hashid;
    private int role;
    private int data;

    public User() {
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.hashsession = Security.MD5(id + name + password);
        this.hashpass = Security.MD5(password);
        this.hashid = Security.MD5(String.format("/d", this.id));
        this.role = 0;

    }

    public User(int id, String name, String password, String hashpass,
                String hashsession, String hashcookie, String hashid, int role, int data) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.hashpass = hashpass;
        this.hashsession = hashsession;
        this.hashcookie = hashcookie;
        this.hashid = hashid;
        this.role = role;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHashpass(String hashpass) {
        this.hashpass = hashpass;
    }

    public void setHashsession(String hashsession) {
        this.hashsession = hashsession;
    }

    public void setHashcookie(String hashcookie) {
        this.hashcookie = hashcookie;
    }

    public void setHashid(String hashid) {
        this.hashid = hashid;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getHashpass() {
        return hashpass;
    }

    public String getHashsession() {
        return hashsession;
    }

    public String getHashcookie() {
        return hashcookie;
    }

    public String getHashid() {
        return hashid;
    }

    public int getRole() {
        return role;
    }

    public int getData() {
        return data;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return password != null ? password.equals(user.password) : user.password == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

}
