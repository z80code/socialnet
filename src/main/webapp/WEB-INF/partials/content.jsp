<%@ page import="com.z80.Models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% User user = (User)request.getAttribute("user"); %>
<section>
    <div class="container">
        <div class="row">
            <div class="col-sm-3">
                <div class="left-sidebar">
                    <h2>Список контактов</h2>
                    <div class="panel-group category-products" id="accordian">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordian" href="#sportswear">
                                        <span class="badge pull-right"><i class="fa fa-plus"></i></span>
                                        Группы
                                    </a>
                                </h4>
                            </div>
                            <div id="sportswear" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <ul>
                                        <li><a href="${pageContext.request.contextPath}#">Нет групп </a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordian" href="#mens">
                                        <span class="badge pull-right"><i class="fa fa-plus"></i></span>
                                        Друзья
                                    </a>
                                </h4>
                            </div>
                            <div id="mens" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <ul>
                                        <li><a href="#">Нет друзей</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>
            </div>
            <% if (user != null) {%>
            <div class="col-sm-9 padding-right">
                <div class="features_items"><!--Friend's events-->
                    <h2 class="title text-center">События друзей</h2>

                </div><!--Friend's events-->

                <div class="recommended_items"><!--recommended_items-->
                    <h2 class="title text-center">Друзья друзей</h2>

                    <div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                            <div class="item active">
                                <div class="col-sm-4">
                                    <div class="product-image-wrapper">
                                        <div class="single-products">
                                            <div class="productinfo text-center">
                                                <h2></h2>
                                                <p>Возможно вы знакомы?</p>
                                                <a href="#" class="btn btn-default"><i class="fa fa-user-plus"></i> Добавить в друзья</a>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="product-image-wrapper">
                                        <div class="single-products">
                                            <div class="productinfo text-center">
                                                <img src="" alt="" />
                                                <h2></h2>
                                                <p>Возможно вы знакомы?</p>
                                                <a href="#" class="btn btn-default"><i class="fa fa-user-plus"></i> Добавить в друзья</a>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="product-image-wrapper">
                                        <div class="single-products">
                                            <div class="productinfo text-center">
                                                <img src="" alt="" />
                                                <h2></h2>
                                                <p>Возможно вы знакомы?</p>
                                                <a href="#" class="btn btn-default"><i class="fa fa-user-plus"></i> Добавить в друзья</a>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="item">
                                <div class="col-sm-4">
                                    <div class="product-image-wrapper">
                                        <div class="single-products">
                                            <div class="productinfo text-center">
                                                <img src="" alt="" />
                                                <h2></h2>
                                                <p>Возможно вы знакомы?</p>
                                                <a href="#" class="btn btn-default"><i class="fa fa-user-plus"></i> Добавить в друзья</a>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="product-image-wrapper">
                                        <div class="single-products">
                                            <div class="productinfo text-center">
                                                <img src="" alt="" />
                                                <h2></h2>
                                                <p>Возможно вы знакомы?</p>
                                                <a href="#" class="btn btn-default"><i class="fa fa-user-plus"></i> Добавить в друзья</a>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4">
                                    <div class="product-image-wrapper">
                                        <div class="single-products">
                                            <div class="productinfo text-center">
                                                <img src="images/home/recommend3.jpg" alt="" />
                                                <h2></h2>
                                                <p>Возможно вы знакомы?</p>
                                                <a href="#" class="btn btn-default"><i class="fa fa-user-plus"></i> Добавить в друзья</a>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <a class="left recommended-item-control" href="#recommended-item-carousel" data-slide="prev">
                            <i class="fa fa-angle-left"></i>
                        </a>
                        <a class="right recommended-item-control" href="#recommended-item-carousel" data-slide="next">
                            <i class="fa fa-angle-right"></i>
                        </a>
                    </div>
                </div><!--/recommended_items-->

            </div>
            <% } %>
        </div>
    </div>
</section>
