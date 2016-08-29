package com.z80.DAO;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;


public class BillingModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(UserDao.class).to(UserDaoImpl.class);
    }
}
