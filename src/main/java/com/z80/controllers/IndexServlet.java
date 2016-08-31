package com.z80.controllers;

import com.google.inject.Singleton;
import com.z80.Models.Page;
import com.z80.Models.PageMode;
import com.z80.Models.User;
import com.z80.helpers.DispatcherHelper;
import com.z80.services.Auth;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Singleton
public class IndexServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Auth authService = new Auth(req, resp);
        Exception error = null;
        User user = null;
        try {
            user = authService.getUser(req, resp);
        } catch (Exception e) {
            if (e instanceof SQLException) {
                error = new Exception("Ошибка соединения с базой.(Error 001)", e);
                //TODO add log errors
            } else if (e instanceof ClassNotFoundException) {
                error = new Exception("Ошибка соединения с базой.(Error 002)", e);
                //TODO add log errors
            }
            else{
                error = new Exception("Неизвестная ошибка.(Error 000a)", e);
                //TODO add log errors
            }
            user = null;
        }
        Page page = new Page("Главная", user, PageMode.Main);
        req.setAttribute("pagedata", page);
        DispatcherHelper.dispatch("index", req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);
//    }
}
