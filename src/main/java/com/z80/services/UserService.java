package com.z80.services;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.z80.DAO.Billing.BillingModule;
import com.z80.DAO.UserRepository;
import com.z80.Models.User;
import com.z80.Models.UserData;
import com.z80.exceptions.AuthException;

import java.sql.SQLException;

public class UserService {
    //TODO not null
    // если залогинен вернет юзера
    public User login(String username, String password) throws AuthException, SQLException, ClassNotFoundException {
        Injector injector = Guice.createInjector(new BillingModule());
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
        Injector injector = Guice.createInjector(new BillingModule());
        UserRepository userRepository = injector.getInstance(UserRepository.class);
        return userRepository.count();// users.get(username.trim());
    }


    public User loginByHash(String hashid, String hashcookie) throws SQLException, ClassNotFoundException {
        Injector injector = Guice.createInjector(new BillingModule());
        UserRepository userRepository = injector.getInstance(UserRepository.class);
        User user = (User) userRepository.findByHash(hashcookie);// users.get(username.trim());
        if (user != null) {
            if (user.getHashid().equals(hashid)) return user;
        }
        return null;
    }


    public boolean userExist(String username) throws SQLException, ClassNotFoundException {
        Injector injector = Guice.createInjector(new BillingModule());
        UserRepository userRepository = injector.getInstance(UserRepository.class);

        User user = (User) userRepository.findByName(username);
        return (user == null) ? false : true;
    }

    public User regUser(User user, UserData userData) throws SQLException, ClassNotFoundException, AuthException {
        Injector injector = Guice.createInjector(new BillingModule());
        UserRepository userRepository = injector.getInstance(UserRepository.class);
        UserDataService userDataService = new UserDataService();
        userData = userDataService.regUserData(userData);
        if (userData != null) {
            if (userExist(user.getName())) {
                throw new AuthException("Username: " + user.getName() + " is busy!");
            }
            user.setData(userData.getUserdata_id());
            return (User) userRepository.save(user);
        }
        else {
            throw new AuthException("Error authorization (Error:015) ");
        }
    }
}