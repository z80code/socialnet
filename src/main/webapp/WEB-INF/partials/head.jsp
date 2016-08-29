<%@ page import="com.z80.Models.PageMode" %><%%>
<%@ page import="com.z80.Models.Page" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<%  Page pageData = (Page)request.getAttribute("pagedata");
    PageMode mode = pageData.getMode();
    String title = "Home";
    switch (mode) {
        case Main:{
            title = "Home";
            break;
        }
        case Account:{
            title = "Account";
            break;
        }
        case Messages:{
            title = "Messages";
            break;
        }
        case Find:{
            title = "Find";
            break;
        }
        case Login:{
            title = "Login";
            break;
        }
        default:{
            title = "Home";
        }
    }
%>
<title><%=title%> | NETspace</title>
<link href="${pageContext.request.contextPath}resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}resources/css/font-awesome.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}resources/css/font-awesome.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}resources/css/prettyPhoto.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}resources/css/price-range.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}resources/css/animate.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}resources/css/main.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}resources/css/responsive.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}resources/css/style.css" rel="stylesheet">
<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}resources/js/html5shiv.js"></script>
<![endif]-->
<link rel="shortcut icon" href="${pageContext.request.contextPath}resources/images/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
      href="${pageContext.request.contextPath}resources/images/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
      href="${pageContext.request.contextPath}resources/images/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
      href="${pageContext.request.contextPath}resources/images/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
      href="${pageContext.request.contextPath}resources/images/ico/apple-touch-icon-57-precomposed.png">

