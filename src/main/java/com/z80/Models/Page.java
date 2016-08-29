package com.z80.Models;

public class Page {
    private User user;
    private PageMode mode;
    private String pageName;
    private Exception error;

    public Page(String pageName, User user, PageMode mode) {
        this.user = user;
        this.mode = mode;
        this.pageName = pageName;
    }

    public User getUser() {
        return user;
    }

    public PageMode getMode() {
        return mode;
    }

    public void setError(Exception error) {
        this.error = error;
    }

    public Exception getError() {
        return error;
    }
}
