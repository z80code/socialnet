<%@ page import="com.z80.Models.Page" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section id="form"><!--form-->
    <div class="container">
        <div class="row">
            <% Page pageData = (Page) request.getAttribute("pagedata");%>
            <%
                if (pageData.getError() != null) {%>
            <div class="alert alert-danger" role="alert">
                <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                <span class="sr-only">Error:</span>
                <%=pageData.getError().getMessage()%>
            </div>
            <%}%>
        </div>
        <div class="row">
            <div class="col-sm-4 col-sm-offset-1">
                <div class="login-form"><!--login form-->
                    <h2>Вход на ваш аккаунт</h2>
                    <form action="" method="post">
                        <input name="username" type="text" placeholder="Имя"/>
                        <input name="password" type="password" placeholder="Пароль"/>
                        <span><input name="remember" type="checkbox" class="checkbox">
								Запомнить меня</span>
                        <button type="submit" class="btn btn-default">Войти</button>
                    </form>
                </div><!--/login form-->
            </div>
            <div class="col-sm-1 text-center">
                <h2 class="or">ИЛИ</h2>
            </div>
            <div class="col-sm-4">
                <div class="signup-form"><!--sign up form-->
                    <h2>Регистрация нового пользователя!</h2>
                    <form action="registration" method="post">
                        <input name="username" type="text" placeholder="Имя"/>
                        <input name="email" type="email" placeholder="Email адрес"/>
                        <input name="password" type="password" placeholder="Пароль"/>
                        <button type="submit" class="btn btn-default">Регистрация</button>
                    </form>
                </div><!--/sign up form-->
            </div>
        </div>
    </div>
</section>
<!--/form-->