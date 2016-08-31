package com.z80.controllers;

import com.google.inject.Singleton;
import com.z80.helpers.DispatcherHelper;
import com.z80.services.Auth;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Auth auth = new Auth(req, resp);
        auth.exit(req, resp);
        resp.sendRedirect("/");
    }
}
