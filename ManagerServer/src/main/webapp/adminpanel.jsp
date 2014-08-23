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
        <script src="js/addUserAJAX.js"></script>
        <script src="js/addEventAJAX.js"></script>
        <script src="js/addGroupAJAX.js"></script>
        <script src="js/usergson.js"></script>
        <script src="js/eventgson.js"></script>
        <script src="js/groupjson.js"></script>
        <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>
        <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
        <link rel="stylesheet" type="text/css" href="css/jquery.autocomplete.css" />
        <link href="css/dopstyle.css" rel="stylesheet" media="screen">
        <link href="css/style.css" rel="stylesheet" media="screen">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        <script src="js/admin.js"></script>

    </head>

    <body>

        <div class="container">

            <div class="row">

                <div class="col-md-3 content_admin" style="overflow:scroll">

                    <h1>Edit users:</h1>

                    <ul class="nav pull-center" role="center">

                        <li class="dropdown" id="menuUser">

                            <a class="dropdown-toggle" data-toggle="dropdown" id="addUser" style="text-align: center; font-size: 15px">Add User</a>

                            <div class="dropdown-menu" style="padding:17px;">

                                <form class="form pull-left" id="formUser" style="margin: 5px">

                                    <div id="userlog"></div>

                                    <input class="form-control" name="userName" id="username" type="text" placeholder="User name" style="margin: 5px">
                                    <input class="form-control" name="password" id="password" type="text" placeholder="password" style="margin: 5px">
                                    <input class="form-control" name="role" id="role" type="text" placeholder="role" style="margin: 5px"><br>

                                    <button type="button" id="btnUser" class="btn btn-default btn-lg btn-block">Add User!</button>

                                </form>

                            </div>

                        </li>

                    </ul>

                    <div id="userInfo"></div>

                </div>

                <div class="col-md-3 content_admin" style="overflow:scroll">

                    <h1>Edit events:</h1>

                    <ul class="nav pull-center" role="center">

                        <li class="dropdown" id="menuEvet">

                            <a class="dropdown-toggle" data-toggle="dropdown" id="addEvent" style="text-align: center; font-size: 15px">Add Event</a>

                            <div class="dropdown-menu" style="padding:17px;">

                                <form class="form pull-left" id="formEvent" style="margin: 5px">

                                    <div id="eventlog"></div>

                                    <input class="form-control" name="eventName" id="eventname" type="text" placeholder="Event name" style="margin: 5px">
                                    <input class="form-control" name="date" data-format="dd/MM/yyyy hh:mm" id="date" type="datetime-local" placeholder="Date" style="margin: 5px">
                                    <input class="form-control" name="coord" id="coord" type="text" placeholder="Address" style="margin: 5px"><br>

                                    <button type="button" id="btnEvent" class="btn btn-default btn-lg btn-block">Add Event!</button>

                                </form>

                            </div>

                        </li>

                    </ul>

                    <div id="eventInfo"></div>

                    <div class="login-help">
                        <a href="/main">Return to main page</a>
                    </div>

                </div>

                <div class="col-md-3 content_admin" style="overflow:scroll">

                    <h1>Edit groups:</h1>

                    <ul class="nav pull-center" role="center">

                        <li class="dropdown" id="menuGroup">

                            <a class="dropdown-toggle" data-toggle="dropdown" id="addGroup" style="text-align: center; font-size: 15px">Add Group</a>

                            <div class="dropdown-menu" style="padding:17px;">

                                <form class="form" id="formGroup" style="margin: 5px">

                                    <div id="grouplog"></div>

                                    <input class="form-control" name="groupName" id="groupname" type="text" placeholder="Group name" style="margin: 5px">
                                    <%--<input class="form-control" name="usersList" id="userslist" type="text" placeholder="Add users/ notwork" style="margin: 5px">--%>
                                    <%--<input class="form-control" name="eventsList" id="eventslist" type="text" placeholder="Add events/ notwork" style="margin: 5px">--%>

                                    <button type="button" id="btnGroup" class="btn btn-default btn-lg btn-block">Add Group!</button>

                                </form>

                            </div>

                        </li>

                    </ul>

                    <div id="groupInfo"></div>

                </div>

            </div>

        </div>

    </body>

</html>
