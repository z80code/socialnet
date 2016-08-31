package com.z80.services;

import com.z80.Models.User;
import com.z80.exceptions.AuthException;

import javax.inject.Singleton;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

@Singleton
public class Auth {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public Auth(HttpServletRequest _request, HttpServletResponse _response) {
        request = _request;
        response = _response;
    }

    /**
     * check auth by cookies data & post request for login
     * @param request
     * @param response
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws AuthException
     */
    public User getUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, AuthException {
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
                if (user != null && getCookieByName(cookie, "hashid").equals(user.getHashid())) {
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
                if (user != null) {
                    if (true) { // TODO do different auth for admin(in session) and user(in cookie) members
                        setCookie(user);
                    }
                }
            }
        }
        return user;
    }

    public void exit(HttpServletRequest req, HttpServletResponse resp) {
        Cookie cookieid = new Cookie("hashid", "");
        cookieid.setMaxAge(0);
        Cookie cookiehashuser = new Cookie("hashcookie", "");
        cookiehashuser.setMaxAge(0);
        resp.addCookie(cookieid);
        resp.addCookie(cookiehashuser);
    }

    public void setCookie(User user) {
        Cookie cookieid = new Cookie("hashid", user.getHashid());
        cookieid.setMaxAge(3600);
        Cookie cookiehashuser = new Cookie("hashcookie", user.getHashcookie());
        cookiehashuser.setMaxAge(3600);
        response.addCookie(cookieid);
        response.addCookie(cookiehashuser);
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
