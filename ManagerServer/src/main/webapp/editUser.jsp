<%@ page import="DAOHandler.UserDataManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html>

    <head>

        <%

            String userName = null;
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("username")) userName = cookie.getValue();
                }
            }

            if (userName == null) {
                response.sendRedirect("login.html");
            }

            UserDataManager manager = new UserDataManager();

            if (!manager.getUserData(userName).getRole().equals("admin")) {
                response.sendRedirect("/main");
            }

            String entityName = request.getParameter("entityName");

        %>

        <meta charset="UTF-8">
        <title id="title">Edit <%=entityName%> profile</title>
        <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>
        <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

        <script type="text/javascript" src="js/editAJAX.js"></script>

    </head>

    <body>

        <form method="post" class="login" id="editForm">

            <div class="login-card">

                <h1 id="userTitle">Edit <%=entityName%> profile</h1><br>

                <div id="userInfo">
                    <div style="color: red" id="editResponse"></div>
                </div>

                <label class="label-text">Edit your name</label>
                <input type="text"   id="user"      name="user"      placeholder="New name">
                <input type="hidden" id="user_name" name="curr_name" value="<%=entityName%>">

                <label class="label-text">Edit your password</label>
                <input type="password" id="old_pwd" name="old_pwd" placeholder="Old password">
                <input type="password" id="new_pwd" name="new_pwd" placeholder="New password">

                <label class="label-text">Edit your role</label>
                <input type="text" id="role" name="role" placeholder="Role">

                <input type="submit" id="submit_edit_user" name="login" class="login login-submit" value="submit">

                <div class="login-help">
                    <a href="adminpanel.jsp">Return to admin panel</a>
                </div>

            </div>

        </form>

    </body>

</html>
