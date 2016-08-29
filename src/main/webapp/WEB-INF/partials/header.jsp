<%@ page import="com.z80.Models.User" %>
<%@ page import="com.z80.Models.Page" %>
<%@ page import="com.z80.Models.PageMode" %>
<% Page pageData = (Page) request.getAttribute("pagedata");%>
<header id="header"><!--header-->
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <div class="header-middle"><!--header-middle-->
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="logo pull-left">
                        <a href="/"><img src="${pageContext.request.contextPath}resources/images/home/logoNS.png"
                                         alt=""/></a>
                    </div>
                    <div class="btn-group pull-right">
                    </div>
                </div>
                <div class="col-sm-8">
                    <div class="shop-menu pull-right">
                        <ul class="nav navbar-nav">
                            <%
                                User user = pageData.getUser();
                                if (user != null) {%>
                            <li><a class="<%=pageData.getMode()==PageMode.Account?" active":""%>"
                                   href="${pageContext.request.contextPath}account"><i class="fa fa-user"></i>
                                Аккаунт</a></li>
                            <li><a class="<%=pageData.getMode()==PageMode.Friends?" active":""%>"
                                   href="${pageContext.request.contextPath}friends"><i class="fa fa-group"></i>
                                Список друзей</a></li>
                            <li><a class="<%=pageData.getMode()==PageMode.Messages?" active":""%>"
                                   href="${pageContext.request.contextPath}messages"><i class="fa  fa-envelope"></i>
                                Сообшения</a></li>
                            <li><a <%=pageData.getMode() == PageMode.Search ? " active" : ""%>
                                    href="${pageContext.request.contextPath}search"><i class="fa  fa-search"></i>
                                Найти</a></li>
                            <%
                                }
                            %>
                            <li><a class="<%=pageData.getMode()==PageMode.Login?" active":""%>"
                                   href="${pageContext.request.contextPath}<%=pageData.getUser()==null?"login":"logout"%>">
                                <i class="fa fa-lock"></i> <%=pageData.getUser()==null?"Вход | Регистрация":"Выход"%></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/header-middle-->

    <div class="header-bottom"><!--header-bottom-->
        <div class="container">
            <div class="row">
                <div class="col-sm-9">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse"
                                data-target=".navbar-collapse">
                            <span class="sr-only">Смена навигации</span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <div class="mainmenu pull-left">
                        <ul class="nav navbar-nav collapse navbar-collapse">
                            <li><a href="${pageContext.request.contextPath}../"
                                   class="<%=pageData.getMode().is(PageMode.Main)?"active":""%>">Главная</a></li>
                            <% if (user != null) {%>
                            <li class="dropdown"><a class="<%=pageData.getMode().is(PageMode.Options)?"active":""%>"
                                                    href="#">Действия<i class="fa fa-angle-down"></i></a>
                                <ul role="menu" class="sub-menu">
                                    <li><a href="${pageContext.request.contextPath}shop.html">Создать группу</a></li>
                                    <li><a href="${pageContext.request.contextPath}product-details.html">Найти
                                        группу</a></li>
                                    <li><a href="${pageContext.request.contextPath}checkout.html">Проверка</a></li>
                                    <li><a href="${pageContext.request.contextPath}cart.html">Послать сообщение</a></li>
                                </ul>
                            </li>
                            <li class="dropdown"><a class="<%=pageData.getMode().is(PageMode.Options)?"active":""%>"
                                                    href="#">Блог<i class="fa fa-angle-down"></i></a>
                                <ul role="menu" class="sub-menu">
                                    <li><a href="${pageContext.request.contextPath}blog-single.html">Свой блог</a></li>
                                    <li><a href="${pageContext.request.contextPath}blog.html">Список блогов</a></li>
                                </ul>
                            </li>
                            <% } %>
                            <li><a class="<%=pageData.getMode().is(PageMode.Options)?"active":""%>"
                                   href="${pageContext.request.contextPath}404.html">404</a></li>
                            <li><a class="<%=pageData.getMode().is(PageMode.Options)?"active":""%>"
                                   href="${pageContext.request.contextPath}contact-us.html">Обратная связь</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="search_box pull-right">
                        <input type="text" placeholder="Поиск"/>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/header-bottom-->
</header>
<!--/header-->
