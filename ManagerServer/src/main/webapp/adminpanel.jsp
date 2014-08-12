<%@ page import="DAOHandler.UserDataManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <%
        String userName = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("username")) userName = cookie.getValue();
            }
        }
        if(userName == null){
            response.sendRedirect("login.html");
        }
        UserDataManager manager = new UserDataManager();
        if(!manager.getUserData(userName).getRole().equals("admin")){
            response.sendRedirect("/main");
        }
    %>
    <meta charset="UTF-8">
    <title id="title">Admin panel</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>
    <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="js/admin.js"></script>

</head>
<body>


<form class="login col-md-12">

    <div class="login-card">


        <h1>Admin Panel</h1>

        <label class="label-text">Users</label>
        <input type="text" name="user" id="users"/>
        <button type="button" class="btn btn-default">Edit Profile</button>

        <label class="label-text">Events</label>
        <input type="text" name="search" id="events"/>
        <button type="button" class="btn btn-default">Edit</button>

        <label class="label-text">Groups</label>
        <input type="text" name="search" id="groups"/>
        <button type="button" class="btn btn-default">Edit</button>

        <div class="login-help">
            <a href="/main">Return to main page</a>
        </div>


    </div>
</form>

</body>
</html>
