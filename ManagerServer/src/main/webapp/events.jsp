<%@ page import="DAOHandler.EventDataManager" %>
<%@ page import="model.Event" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="top.jsp" %>

<!DOCTYPE html>
<html>
<head>

    <%
        String eventname = request.getParameter("eventname");
        EventDataManager manager = (EventDataManager) webAppCtx.getBean("eventDataManager");
        Event event = manager.getEventCompleteData(eventname);
        String userName = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("username")) userName = cookie.getValue();
            }
        }
        if(event == null){
            if(userName == null){
                response.sendRedirect("login.html");
            }
            response.sendRedirect("main.jsp");
        }
    %>
    <meta charset="UTF-8">
    <title id="title">Event <%=eventname%></title>
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
        <div class="col-md-12 content" style="overflow:scroll">


            <h1>Event <div id="event"><%=eventname%></div></h1>
            <%--<%String eventInfo = event.toString();%>--%>
            <%--<div class="col-md" style="font-size: 25px">--%>
            <%--<%=eventInfo%>--%>
            <%--</div>--%>

            <label>Address:</label>
            <div><%=event.getCoordinates().toString()%></div>
            <label>Date:</label>
            <div><%=event.getDate().toString()%></div>
            <label>Admin event:</label>
            <div><%=event.getEventAdmin().toString()%></div>
            <label>Users:</label>
            <div><%
                ArrayList<String> username = new ArrayList<>();
                for(User user: event.getUsers() ){
                    username.add(user.getUserName());
                }%>
                <%=username.toString()%></div>
            <label>Comments:</label>
            <div id="messages"><%--<%=event.getComments().toString()%>--%></div>
            <div class="input-group col-sm-3" style="padding-bottom: 5px">
                <input type="text" class="form-control" id="inputComment">
                <span class="input-group-btn">
                  <button class="btn btn-default" id="btnComment"  onclick="addComment()" type="button">OK!</button>
                </span>
            </div>
            <button type="button" class="btn btn-default" id="eventAdd">Add me!</button>
            <div class="login-help">
                <a href="/main">Return to main page</a>
            </div>

        </div>
    </div>
</div>

</body>
</html>
