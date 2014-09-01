<%@ page import="DAOHandler.GroupDataManager" %>
<%@ page import="model.Groupp" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="top.jsp" %>

<!DOCTYPE html>
<html>
<head>

    <%
        String groupname = request.getParameter("groupname");
        GroupDataManager manager = (GroupDataManager) webAppCtx.getBean("groupDataManager");
        Groupp group = manager.getGroupCompleteData(groupname);
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
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script src="js/AJAXAddUserToGroupEvent.js"></script>
    <link href="css/dopstyle.css" rel="stylesheet" media="screen">
    <link href="css/style.css" rel="stylesheet" media="screen">
    <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>
    <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-md-6 content" style="overflow:scroll">

            <h1>Group <div id="group"><%=groupname%></div></h1>
            <%--<%String groupInfo = group.toString();%>--%>
            <%--<div class="col-md-3" style="font-size: 25px">--%>
            <%--<%=groupInfo%>--%>
            <%--</div>--%>
            <label>Admin group:</label>
            <div><%=group.getGroupAdmin().toString()%></div>
            <label>Users:</label>
            <div><%
                ArrayList<String> username = new ArrayList<>();
                for(User user: group.getUsers()){
                    username.add(user.getUserName());
                }
            %>
                <%=username.toString()%></div>
            <button type="button" class="btn btn-default" id="groupAdd" >Add me!</button>
            <div class="login-help col-sm-3">
                <a href="/main">Return to main page</a>
            </div>
        </div>
    </div>
</div>
</div>

</body>
</html>
