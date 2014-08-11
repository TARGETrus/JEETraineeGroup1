<%@ page import="DAOHandler.EventDataManager" %>
<%@ page import="model.Event" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String eventname = request.getParameter("eventname");
        EventDataManager manager = new EventDataManager();
        Event event= manager.getEventCompleteData(eventname);
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
    <link rel='stylesheet' href='http://codepen.io/assets/libs/fullpage/jquery-ui.css'>
    <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>

<form class="login">

    <div class="login-card">
        <h1>Event <%=eventname%></h1>
        <%String eventInfo = event.toString();%>
        <div class="col-md" style="font-size: 25px">
            <%=eventInfo%>
        </div>

    </div>
</form>
</body>
</html>