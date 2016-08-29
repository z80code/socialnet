package com.z80.services;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.z80.DAO.BillingModule;
import com.z80.DAO.UserDao;
import com.z80.DAO.UserDaoImpl;
import com.z80.Models.PageMode;
import com.z80.Models.User;
import com.z80.exceptions.AuthException;

import javax.inject.Singleton;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Singleton
public class Auth {

    public Auth() {
    }

    public User getUser(HttpServletRequest request, HttpServletResponse resp) throws SQLException, ClassNotFoundException, AuthException {
        UserService userService = new UserService();
        User user = null;
        String userName = request.getParameter("username");
        String password = request.getParameter("password");
        if (userName == null || password == null || userName.isEmpty() || password.isEmpty()) {
             Cookie[] cookie = request.getCookies();

            String hashcookie = getCookieByName(cookie, "hashcookie");
            if (hashcookie != null) {
                String hashid = getCookieByName(cookie, "hashid");
                user = userService.loginByHash(hashid, hashcookie);
                if (user != null && getCookieByName(cookie, "hashid")==user.getHashid()) { // set session
                    return user;
                }

            }
            String usernameCookies = getCookieByName(cookie, userName);
            if (usernameCookies != null) {
                // Совпадает?
                if (usernameCookies.equals(userName)) {
                } else {
                }
            } else {
                // addCookies
            }
        } else {
            if (request.getMethod().equals("POST")) {
                user = userService.login(userName, password);
                if (user != null) { // set session
                    Cookie cookieid = new Cookie("hashid", user.getHashid());
                    cookieid.setMaxAge(3600);
                    Cookie cookiehashuser = new Cookie("hashcookie", user.getHashcookie());
                    cookiehashuser.setMaxAge(3600);
                    resp.addCookie(cookieid);
                    resp.addCookie(cookiehashuser);
                }
            }
        }
        return user;
    }
public void exit(HttpServletRequest req, HttpServletResponse resp){
    Cookie cookieid = new Cookie("hashid", "");
    cookieid.setMaxAge(0);
    Cookie cookiehashuser = new Cookie("hashcookie", "");
    cookiehashuser.setMaxAge(0);
    resp.addCookie(cookieid);
    resp.addCookie(cookiehashuser);
}

    private String getCookieByName(Cookie[] cookie, String name) {
        for (Cookie cook : cookie) {
            if (cook.getName().equals(name)) {
                return cook.getValue();
            }
        }
        return null;
    }

}
