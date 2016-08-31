package com.z80.DAO.Billing;

import com.google.inject.AbstractModule;
import com.z80.DAO.Core.Repository;
import com.z80.DAO.UserDataRepository;
import com.z80.DAO.UserRepository;

public class BillingModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Repository.class).to(UserRepository.class);
        bind(Repository.class).to(UserDataRepository.class);
    }
}
