package com.z80;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.z80.controllers.IndexServlet;
import com.z80.controllers.LoginServlet;
import com.z80.controllers.LogoutServlet;

import javax.servlet.annotation.WebListener;

@WebListener
public class MyGuiceServletConfig extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new ServletModule()
        {
            @Override
            protected void configureServlets(){
                serve("/login").with(LoginServlet.class);
                serve("/logout").with(LogoutServlet.class);
                serve("/").with(IndexServlet.class);
            }
        });
    }
}