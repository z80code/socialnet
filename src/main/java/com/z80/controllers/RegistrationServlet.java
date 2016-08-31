package com.z80.controllers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.z80.Models.Page;
import com.z80.Models.PageMode;
import com.z80.Models.User;
import com.z80.exceptions.AuthException;
import com.z80.services.Auth;
import com.z80.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@Singleton
public class RegistrationServlet extends HttpServlet {

    @Inject
    UserService userService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        boolean _error = username == null || password == null || username.trim().isEmpty() || password.isEmpty();
        Exception error = null;
        User user = null;
        if (!_error) {
            try {
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                //TODO
                Auth authService = new Auth(req, resp);
                user = authService.getUser(req, resp);

            } catch (Exception e) {
                if (e instanceof SQLException) {
                    error = new Exception("Ошибка соединения с базой.(Error 001)", e);
                } else if (e instanceof ClassNotFoundException) {
                    error = new Exception("Ошибка соединения с базой.(Error 002)", e);
                } else if (e instanceof AuthException) {
                    error = new Exception("Ошибка соединения с базой.(Error 002)", e);
                }
            }
        }

        if (user == null) {
            UserService userService = new UserService();
            try {
                user = userService.regUser(username, password, email);
            } catch (Exception e) {
                if (e instanceof SQLException) {
                    error = new Exception("Ошибка соединения с базой.(Error 001)", e);
                } else if (e instanceof ClassNotFoundException) {
                    error = new Exception("Ошибка соединения с базой.(Error 002)", e);
                } else if (e instanceof AuthException) {
                    error = new Exception("Ошибка соединения с базой.(Error 002)", e);
                }
            }
        }
        Page page = new Page("Вход | Регистрация", user, PageMode.Login);
        req.setAttribute("pagedata", page);
        if (user != null) resp.sendRedirect("/");

    }
}