package com.z80.services;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.z80.DAO.Billing.UserBillingModule;
import com.z80.DAO.Billing.UserDataBillingModule;
import com.z80.DAO.UserDataRepository;
import com.z80.Models.UserData;
import com.z80.exceptions.AuthException;

import java.sql.SQLException;

public class UserDataService {

        public long getCount() throws SQLException, ClassNotFoundException {
            Injector injector = Guice.createInjector(new UserDataBillingModule());
            UserDataRepository userDataRepository = injector.getInstance(UserDataRepository.class);
            return userDataRepository.count();
        }


        public UserData getByHash(String hash) throws SQLException, ClassNotFoundException {
            Injector injector = Guice.createInjector(new UserDataBillingModule());
            UserDataRepository userDataRepository = injector.getInstance(UserDataRepository.class);
            return (UserData)userDataRepository.findByHash(hash);
        }


        public boolean userDataExist(String hash) throws SQLException, ClassNotFoundException {
            Injector injector = Guice.createInjector(new UserDataBillingModule());
            UserDataRepository userdataRepository = injector.getInstance(UserDataRepository.class);

            UserData user = (UserData)userdataRepository.findByHash(hash);
            return (user == null) ? false : true;
        }

        public UserData regUserData(UserData userData) throws SQLException, ClassNotFoundException, AuthException {
            Injector injector = Guice.createInjector(new UserDataBillingModule());
            UserDataRepository userDataRepository = injector.getInstance(UserDataRepository.class);
            return (UserData)userDataRepository.save(userData);
        }
    }