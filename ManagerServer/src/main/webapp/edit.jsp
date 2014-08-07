<%@page language="java" contentType="text/html; charset=US-ASCII"
        pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
    %>


    <meta charset="UTF-8">
    <title>Edit profile</title>
    <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>
    <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <!--<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>-->
    <script type="text/javascript" src="js/editJSON.js"></script>
</head>
<body>
<form method="post" class="login" id="editForm">

    <div class="login-card">


        <h1>Edit profile</h1><br>
        <div id="userInfo">
            <div style="color: red" id="editResponse"></div>
        </div>
        <label class="label-text">Edit your name</label>
        <input type="text" id="user" name="user" placeholder="New name">

        <label class="label-text">Edit your password</label>
        <input type="password" id="old_pwd" name="old_pwd" placeholder="Old password">
        <input type="password" id="new_pwd" name="new_pwd" placeholder="New password">
        <input type="submit" id="submit_edit" name="login" class="login login-submit" value="submit">


        <div class="login-help">
            <a href="events.jsp">Return to main page</a>
        </div>

    </div>
</form>
</body>
