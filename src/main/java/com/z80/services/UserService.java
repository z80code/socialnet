package com.z80.services;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.z80.DAO.BillingModule;
import com.z80.DAO.UserDao;
import com.z80.exceptions.AuthException;
import com.z80.Models.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    //TODO not null
    // если залогинен вернет юзера
    public User login(String username, String password) throws AuthException, SQLException, ClassNotFoundException {
        Injector injector = Guice.createInjector(new BillingModule());
        UserDao userDao = injector.getInstance(UserDao.class);

        User user = userDao.findByName(username);// users.get(username.trim());
        if(user ==null) {
            throw new AuthException("Username: " + username + "  not found!");
        }
        if(user.getPassword().equals(password)){
            return user;
        }
        throw new AuthException("User not exist!");
    }

    public User loginByHash(String hashid, String hashcookie) throws SQLException, ClassNotFoundException {
        Injector injector = Guice.createInjector(new BillingModule());
        UserDao userDao = injector.getInstance(UserDao.class);
        User user = userDao.findByHash(hashcookie);// users.get(username.trim());
        if (user!=null){
            if(user.getHashid().equals(hashid)) return user;
        }
        return null;
    }


    public boolean userExist(String username) throws AuthException, SQLException, ClassNotFoundException {
        Injector injector = Guice.createInjector(new BillingModule());
        UserDao userDao = injector.getInstance(UserDao.class);

        User user = userDao.findByName(username);
        return (user==null)?false:true;
    }


}
