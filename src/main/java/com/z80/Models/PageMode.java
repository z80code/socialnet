package com.z80.Models;

public enum PageMode {
    Main,
    Friends,
    Search,
    Login,
    Find,
    Options,
    Default,
    Messages,
    Account,
    LoginForm,
    YouAreLogged,
    AddCookies,
    YouAreLoggedWithNewName,
    Delete;

    public boolean is(PageMode mode){
        return this==mode;
    }


}