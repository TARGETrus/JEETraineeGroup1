<%@ page import="DAOHandler.GroupDataManager" %>
<%@ page import="model.Group" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String groupname = request.getParameter("groupname");
        GroupDataManager manager = new GroupDataManager();
        Group group = manager.getGroupCompleteData(groupname);
        String userName = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("username")) userName = cookie.getValue();
            }
        }
        if(group == null){
            if(userName == null){
                response.sendRedirect("login.html");
            }
            response.sendRedirect("main.jsp");
        }

    %>
    <meta charset="UTF-8">
    <title id="title">Group <%=groupname%></title>
    <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>
    <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>

<form class="login">

    <div class="login-card">
        <h1>Group <%=groupname%></h1>
        <%String groupInfo = group.toString();%>
        <div class="col-md-3" style="font-size: 25px">
            <%=groupInfo%>
        </div>

    </div>
</form>
</body>
</html>
