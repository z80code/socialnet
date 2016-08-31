package com.z80.controllers;

import com.google.inject.Singleton;
import com.z80.Models.User;
import com.z80.exceptions.AuthException;
import com.z80.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

@Singleton
public class Test extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer writer = resp.getWriter();
        resp.setContentType("text/html;charset=UTF-8");
        writer.write("<head><meta charset='utf-8'></head>");
        writer.write("we-we");
        User user = null;
        UserService userServ = new UserService();
        long val =0;
        try {
            val = userServ.getCount();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            user = userServ.login("admin","1234");

        } catch (AuthException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        writer.write(user.toString());
        writer.write(String.format("%d",val));
    }
}
