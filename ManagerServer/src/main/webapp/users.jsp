<%@ page import="DAOHandler.UserDataManager" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="top.jsp" %>

<html>
<head>
    <%
        String username = request.getParameter("username");
        UserDataManager manager = (UserDataManager) webAppCtx.getBean("userDataManager");
        User user = manager.getUserCompleteData(username);
        String userName = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("username")) userName = cookie.getValue();
            }
        }
        if(user == null){
            if(userName == null){
                response.sendRedirect("login.html");
            }
            response.sendRedirect("main.jsp");
        }

    %>
    <meta charset="UTF-8">
    <title id="title">Group <%=username%></title>
    <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>
    <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>

<form class="login">

    <div class="login-card">
        <h1>User: <%=username%></h1>
        <%String userInfo = user.toString();%>
        <div class="col-md-3" style="font-size: 25px">
            <%=userInfo%>
        </div>

    </div>
</form>
</body>

</html>
