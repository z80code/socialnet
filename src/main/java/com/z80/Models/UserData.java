package com.z80.Models;

public class UserData {
    public static final String TABLE_NAME = "usersdata";
    public static final String USERDATA_ID = "userdata_id";
    public static final String USERDATA_HASH = "userdata_id";
    public static final String USERDATA_FIRSTNAME = "userdata_firstname";
    public static final String USERDATA_LASTNAME = "userdata_lastname";
    public static final String USERDATA_EMAIL = "userdata_email";
    public static final String USERDATA_HOBBY = "userdata_hobby";

    public UserData(int userdata_id, String userdata_hash, String userdata_firstname, String userdata_lastname, String userdata_email, String userdata_hobby) {
        this.userdata_id = userdata_id;
        this.userdata_hash = userdata_hash;
        this.userdata_firstname = userdata_firstname;
        this.userdata_lastname = userdata_lastname;
        this.userdata_email = userdata_email;
        this.userdata_hobby = userdata_hobby;
    }

    public void setUserdata_id(int userdata_id) {
        this.userdata_id = userdata_id;
    }

    public void setUserdata_hash(String userdata_hash) {
        this.userdata_hash = userdata_hash;
    }

    public void setUserdata_firstname(String userdata_firstname) {
        this.userdata_firstname = userdata_firstname;
    }

    public void setUserdata_lastname(String userdata_lastname) {
        this.userdata_lastname = userdata_lastname;
    }

    public void setUserdata_email(String userdata_email) {
        this.userdata_email = userdata_email;
    }

    public void setUserdata_hobby(String userdata_hobby) {
        this.userdata_hobby = userdata_hobby;
    }

    public int getUserdata_id() {
        return userdata_id;
    }

    public String getUserdata_hash() {
        return userdata_hash;
    }

    public String getUserdata_firstname() {
        return userdata_firstname;
    }

    public String getUserdata_lastname() {
        return userdata_lastname;
    }

    public String getUserdata_email() {
        return userdata_email;
    }

    public String getUserdata_hobby() {
        return userdata_hobby;
    }

    private int userdata_id;
    private String userdata_hash;
    private String userdata_firstname;
    private String userdata_lastname;
    private String userdata_email;
    private String userdata_hobby;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserData userData = (UserData) o;

        if (getUserdata_id() != userData.getUserdata_id()) return false;
        if (getUserdata_firstname() != null ? !getUserdata_firstname().equals(userData.getUserdata_firstname()) : userData.getUserdata_firstname() != null)
            return false;
        if (getUserdata_lastname() != null ? !getUserdata_lastname().equals(userData.getUserdata_lastname()) : userData.getUserdata_lastname() != null)
            return false;
        if (getUserdata_email() != null ? !getUserdata_email().equals(userData.getUserdata_email()) : userData.getUserdata_email() != null)
            return false;
        return getUserdata_hobby() != null ? getUserdata_hobby().equals(userData.getUserdata_hobby()) : userData.getUserdata_hobby() == null;

    }

    @Override
    public int hashCode() {
        int result = getUserdata_id();
        result = 31 * result + (getUserdata_hash() != null ? getUserdata_hash().hashCode() : 0);
        result = 31 * result + (getUserdata_firstname() != null ? getUserdata_firstname().hashCode() : 0);
        result = 31 * result + (getUserdata_lastname() != null ? getUserdata_lastname().hashCode() : 0);
        result = 31 * result + (getUserdata_email() != null ? getUserdata_email().hashCode() : 0);
        result = 31 * result + (getUserdata_hobby() != null ? getUserdata_hobby().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userdata_id=" + userdata_id +
                ", userdata_hash='" + userdata_hash + '\'' +
                ", userdata_firstname='" + userdata_firstname + '\'' +
                ", userdata_lastname='" + userdata_lastname + '\'' +
                ", userdata_email='" + userdata_email + '\'' +
                ", userdata_hobby='" + userdata_hobby + '\'' +
                '}';
    }
}
