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
    <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>
    <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="js/admin.js"></script>

</head>
<body>


<form class="login">

    <div class="login-card">
        <h1>Admin Panel</h1>


            <%--<div id="userInfo">--%>
                <%--<div style="color: red" id="editResponse"></div>--%>
            <%--</div>--%>
            <%--<label class="label-text">Edit your name</label>--%>
            <%--<input type="text" id="user" name="user" placeholder="New name">--%>

            <%--<label class="label-text">Edit your password</label>--%>
            <%--<input type="password" id="old_pwd" name="old_pwd" placeholder="Old password">--%>
            <%--<input type="password" id="new_pwd" name="new_pwd" placeholder="New password">--%>
            <%--<input type="submit" id="submit_edit" name="login" class="login login-submit" value="submit">--%>


            <%----%>
            <label class="label-text">Users</label>
            <input name="user" id="users"/>
            <button class="btn">button</button>

                <label class="label-text">Events</label>
                <input name="search" id="events"/>
                <button class="btn">button</button>

                <label class="label-text">Groups</label>
                <input name="search" id="groups"/>
                <button class="btn">button</button>

            <div class="login-help">
                <a href="/main">Return to main page</a>
            </div>


    </div>
</form>

</body>
</html>
