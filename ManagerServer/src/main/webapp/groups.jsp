<%@ page import="DAOHandler.GroupDataManager" %>
<%@ page import="model.Groupp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String groupname = request.getParameter("groupname");
        GroupDataManager manager = new GroupDataManager();
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
            <div class="col-md-12 content">

                <h1>Group <div id="group"><%=groupname%></div></h1>
                <%String groupInfo = group.toString();%>
                <div class="col-md-3" style="font-size: 25px">
                    <%=groupInfo%>
                </div>
                <button type="button" class="btn btn-default" id="groupAdd" >Add me!</button>
            </div>
        </div>
    </div>
    </div>

</body>
</html>
