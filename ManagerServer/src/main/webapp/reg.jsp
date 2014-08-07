<%@ page import="DAOHandler.UserDataManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% DAOHandler.UserDataManager manager = new DAOHandler.UserDataManager();%>
<%request.setAttribute("manager_", manager);%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>
    <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript" src="js/regJSON.js"></script>
    <!--<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>-->


</head>

<body>
<form method="post" class="login" id="ajaxForm"><!--action="LoginServlet"-->

    <div class="login-card" id="myAjaxRequestForm">


        <h1>Registration on EventManager</h1><br>
        <div id="userInfo">
            <div style="color: red" id="ajaxResponse"></div>
        </div>
        <input type="text" id="name" name="user" placeholder="Enter your name" required />
        <input type="text" id="repeat_name" name="repeat_user" placeholder="Repeat your name" required />
        <input type="text" id="password" name="password" placeholder="Enter your password" required />
        <input type="text" id="repeat_password" name="repeat_password" placeholder="Repeat your password" required />
        <input type="submit" id="submit" name="login" class="login login-submit" value="Register!">

        <%--<button type="submit" name="login" class="login login-submit" id="myButton">Register!!</button>--%>

    </div>

</form>
</body>

</html>
</html>