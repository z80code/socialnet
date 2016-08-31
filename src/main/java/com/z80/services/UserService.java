package com.z80.services;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.z80.DAO.Billing.UserBillingModule;
import com.z80.DAO.UserRepository;
import com.z80.Models.User;
import com.z80.exceptions.AuthException;

import java.sql.SQLException;

public class UserService {
    //TODO not null
    // если залогинен вернет юзера
    public User login(String username, String password) throws AuthException, SQLException, ClassNotFoundException {
        Injector injector = Guice.createInjector(new UserBillingModule());
        UserRepository userRepository = injector.getInstance(UserRepository.class);

        User user = (User) userRepository.findByName(username);// users.get(username.trim());

        if (user == null) {
            throw new AuthException("Username: " + username + "  not found!");
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        throw new AuthException("User not exist!");
    }

    public long getCount() throws SQLException, ClassNotFoundException {
        Injector injector = Guice.createInjector(new UserBillingModule());
        UserRepository userRepository = injector.getInstance(UserRepository.class);
        return userRepository.count();// users.get(username.trim());
    }


    public User loginByHash(String hashid, String hashcookie) throws SQLException, ClassNotFoundException {
        Injector injector = Guice.createInjector(new UserBillingModule());
        UserRepository userRepository = injector.getInstance(UserRepository.class);
        User user = (User)userRepository.findByHash(hashcookie);// users.get(username.trim());
        if (user != null) {
            if (user.getHashid().equals(hashid)) return user;
        }
        return null;
    }


    public boolean userExist(String username) throws SQLException, ClassNotFoundException {
        Injector injector = Guice.createInjector(new UserBillingModule());
        UserRepository userRepository = injector.getInstance(UserRepository.class);

        User user = (User)userRepository.findByName(username);
        return (user == null) ? false : true;
    }

    public User regUser(String username, String password, String email) throws SQLException, ClassNotFoundException, AuthException {
        Injector injector = Guice.createInjector(new UserBillingModule());
        UserRepository userRepository = injector.getInstance(UserRepository.class);

        if (userExist(username)) {
            throw new AuthException("Username: " + username + " is busy!");
        }

        String _name = username;
        String _password = password;
        String _hashpass = "";
        String _hashsession = "";
        String _hashcookie = "";
        String _hashid = "";
        int _role = 0;
        int _data = 1;
        User user = (User)userRepository.save(new User(0, _name, _password, _hashpass, _hashsession, _hashcookie, _hashid, _role, _data));
        return user;
    }


}