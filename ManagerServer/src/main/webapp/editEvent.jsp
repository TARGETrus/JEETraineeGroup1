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
        <title id="title">Edit <%=entityName%> event</title>
        <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>
        <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>

        <script type="text/javascript" src="js/editAJAX.js"></script>

    </head>

    <body>

        <form method="post" class="login" id="editForm">

            <div class="login-card">

                <h1 id="eventTitle">Edit <%=entityName%> event</h1><br>

                <div id="userInfo">
                    <div style="color: red" id="editResponse"></div>
                </div>

                <label class="label-text">Edit event name</label>
                <input type="text"   id="event"      name="event"     placeholder="New name">
                <input type="hidden" id="event_name" name="curr_name" value="<%=entityName%>">

                <label class="label-text">Edit properties</label>
                <input type="text"           id="coordinates" name="coordinates" placeholder="new coordinates">
                <input type="text"           id="latitude"    name="latitude"    placeholder="new latitude">
                <input type="text"           id="longitude"   name="longitude"   placeholder="new longitude">
                <input type="datetime-local" id="date"        name="date"        placeholder="new date" data-format="dd/MM/yyyy hh:mm">
                <input type="text"           id="event_admin" name="event_admin" placeholder="new event admin">

                <input type="submit" id="submit_edit_event" name="login" class="login login-submit" value="submit">

                <div class="login-help">
                    <a href="adminpanel.jsp">Return to admin panel</a>
                </div>

            </div>

        </form>

    </body>

</html>
