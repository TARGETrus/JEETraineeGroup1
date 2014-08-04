<%@page import="model.User"%>
<%@page import="model.Group"%>
<%@ page language="java" contentType="text/html; charset=US-ASCII"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>EventManagerMainPage</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/dopstyle.css" rel="stylesheet" media="screen">
    <script src="js/bootstrap.min.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="../../assets/js/html5shiv.js"></script>
    <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
    <script language="javascript">

        function fillMembers(){
            var groups = document.getElementById("groups");
            var selectedValue = groups.options[groups.selectedIndex].value;

            document.getElementById("gMembers").innerHTML = selectedValue;
        }

    </script>
</head>
<body>
<%
    String userName = null;
    Cookie[] cookies = request.getCookies();
    if(cookies != null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("username")) userName = cookie.getValue();
        }
    }
    if(userName == null)
        response.sendRedirect("login.html");
%>

<%
    User user = (User) session.getAttribute("user");//возвращает null....надо думать
%>

<div class="container">
    <div class="row">
        <div class="col-md-12 head-block">
            <h1 align="center">ManagerEvent</h1>
            <form action="LogoutServlet" method="post">
                <p style="text-align: right;">
                    <label>Hi <%=userName %>, Login was successful.</label>
                    <button type="submit">Logout</button>
                </p>
            </form>

        </div>
        <div class="col-md-12 top-menu">
            Menu
        </div>
        <div class="col-md-3 content">
            <h2>Events</h2>
            <p style="text-align: center;">
                <select multiple="false" id="groups" onchange="fillMembers();">
                    <%

                        java.util.Iterator<Group> itr = (user!=null) ?
                                user.getGroups().iterator():null;
                        while(itr!=null && itr.hasNext()) {
                            Group group = itr.next();
                            String gname = group.getGroupName();

                            String nameList= "";
                            java.util.Iterator<User> gitr = group.getUsers().iterator();
                            while(gitr.hasNext()) {
                                User next = gitr.next();
                                nameList = nameList + next.getUserName() + "<br>";
                            }

                    %>
                    <option value="<%=nameList%>"><%=gname%></option>
                    <%
                        }
                    %>
                </select>
            </p>
            <p style="text-align: center;" id="gMembers">outmembers</p>
        </div>
        <div class="col-md-6">
            <div class=".col-sm-12 top-menu">
                Filters
            </div>
            <div class="col-md-12 map_content">
                <h2>Map</h2>
            </div>
        </div>
        <div class="col-md-3 content">
            <h2>Groups</h2>
        </div>
    </div>
</div>


</body>
</html>
