<%@page import="model.User"%>
<%@page language="java" contentType="text/html; charset=US-ASCII"
        pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.google.common.collect.Maps" %>
<%@ page import="servlets.MapServlet" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="util.JsonReader" %>

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
        if(userName == null)
            response.sendRedirect("login.html");
    %>
    <%
        User user = (User) session.getAttribute("user");


    %>
    <meta charset="UTF-8">
    <title>PartyMap</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <script src="js/addEventAJAX.js"></script>
    <script src="js/addGroupAJAX.js"></script>
    <!-- <script src="js/mapClickAJAX.js"></script>-->
    <script src="js/groupjson.js"></script>
    <script src="js/eventgson.js"></script>
    <link href="css/dopstyle.css" rel="stylesheet" media="screen">
    <link href="css/style.css" rel="stylesheet" media="screen">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">

    <style>
        html, body, #map-canvas {
            height: 470px;
            padding: 5px;
        }
    </style>
    <%
        String[] lat;
        String[] lon;
        List<String> latitude = new ArrayList<>();
        List<String> longitude = new ArrayList<>();

        for (Cookie cookie : request.getCookies()) {
            if (cookie.getName().equals("latitude")) {
                lat = cookie.getValue().split(" ");
                for(int i = 0; i < lat.length; i++) {
                    latitude.add(lat[i]);
                }
            }
            if (cookie.getName().equals("longitude")) {
                lon = cookie.getValue().split(" ");
                for(int i = 0; i < lon.length; i++) {
                    longitude.add(lon[i]);
                }
            }
        }

        latitude.add("55");
        longitude.add("33");
        latitude.add("0");
        longitude.add("33");

    %>
    <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAZR0VB_Fk4ZOdDmAwhda8RZsvkwCDFcps&sensor=FALSE"></script>
    <script>
        var latitude;
        var longitude;
        function initialize() {
            var mapOptions = {
                zoom: 10,
                center: new google.maps.LatLng(59.944126, 30.246234)
            };

            var map = new google.maps.Map(document.getElementById('map-canvas'),
                    mapOptions);

            <!--for map click ajax-->


            google.maps.event.addListener(map, "click", function (event) {
                latitude = event.latLng.lat();
                longitude = event.latLng.lng();
                $.ajax({
                    url: "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + "," + longitude + "&sensor=false",
                    type: "POST",
                    success: function (res) {
                        var address = res.results[0].formatted_address;

                        $("#addEvent").click();
                        $(".form-control").val("")
                        $("#coord").val(address);


                    }
                });
            });

            <%
                final String baseUrl = "http://maps.googleapis.com/maps/api/geocode/json";// путь к Geocoding API по HTTP
                final Map<String, String> params = Maps.newHashMap();
                params.put("language", "engl");// язык данных, на котором мы хотим получить
                params.put("sensor", "false");// исходит ли запрос на геокодирование от устройства с датчиком местоположения

                for(int i = 0; i < latitude.size(); i++) {
                    String latlng = latitude.get(i) + "," + longitude.get(i);
                    params.put("latlng", latlng);
                    final String url = baseUrl + '?' + MapServlet.encodeParams(params);// генерируем путь с параметрами
                    final JSONObject resp = JsonReader.read(url);// делаем запрос к вебсервису и получаем от него ответ
                    final JSONObject location = resp.getJSONArray("results").getJSONObject(0);
                    String formattedAddress = location.getString("formatted_address");
            %>

            var marker = new google.maps.Marker({
                position: new google.maps.LatLng(<%= latitude.get(i)%>,<%= longitude.get(i)%>),
                map: map,
                title:"<%=formattedAddress%>"
            });


            <%
                }
            %>
        }
        google.maps.event.addDomListener(window, 'load', initialize);
    </script>
</head>
<body>


<div class="container">
    <div class="row">
        <div class="col-md-12 head-block" style="background: url('img/logo_head.png') no-repeat; background-color: #F7F7F7;">
            <form action="LogoutServlet"  method="post">

                <p style="text-align: right">
                    <label>Hi <%=userName %>, Login was successful.</label>

                <div class="btn-group" style="float: right">
                    <button type="button" class="btn btn-default" onclick="window.location.href='/edit.jsp'">Edit Profile</button>
                    <%
                        if(user.getRole().equals("admin")){
                    %><button type="button" class="btn btn-default"  id="adminBtn" onclick="window.location.href='/adminpanel'">Admin panel</button><%
                    }
                %>
                    <input type="submit" class="btn btn-default" value="Logout">
                </div>
                </p>

            </form>

        </div>
        <div class="col-md-3 content">
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
            <div id="eventInfo">
                <%--<br><button id="jsonEventBtn">JSON</button></br>--%>
            </div>
        </div>
        <div class="col-md-6">
            <%--<div class=".col-sm-12 top-menu">--%>
                <%--<ul class="nav pull-center" role="center">--%>
                <%--<li class="dropdown" id="filterMenu">--%>
                <%--<a class="dropdown-toggle" data-toggle="dropdown" id="addFilter" style="text-align: center; font-size: 15px">Add Event</a>--%>
                <%--<div class="dropdown-menu" style="padding:17px;">--%>
                <%--<form class="form pull-left" id="formFilter" style="margin: 5px">--%>
                <%--<div id="filterlog"></div>--%>
                <%--<input class="form-control" name="eventFilter" id="eventFilter" type="text" placeholder="Event name" style="margin: 5px">--%>
                <%--<div class="input-group">--%>
                <%--<input type="text" id="radius" name="radius" class="form-control" placeholder="В радиосе">--%>
                <%--<span class="input-group-addon"> км</span>--%>
                <%--</div>--%>
                <%--<input class="form-control" name="userFilter" id="userFilter" type="text" placeholder="User name" style="margin: 5px"><br>--%>
                <%--<button type="button" id="btnFilter" class="btn btn-default btn-lg btn-block">Add Filter!</button>--%>
                <%--</form>--%>
                <%--</div>--%>
                <%--</li>--%>
                <%--</ul>--%>

            <%--</div>--%>

            <div class="col-md-12 map_content">
                <%--<jsp:include page="map.jsp"/>--%>
                    <ul class="nav pull-center" role="center">
                        <li class="dropdown" id="filterMenu">
                            <a class="dropdown-toggle" data-toggle="dropdown" id="addFilter" style="text-align: center; font-size: 15px">Add Event</a>
                            <div class="dropdown-menu" style="padding:17px;">
                                <form class="form pull-left" id="formFilter" style="margin: 5px">
                                    <div id="filterlog"></div>
                                    <input class="form-control" name="eventFilter" id="eventFilter" type="text" placeholder="Event name" style="margin: 5px">
                                    <div class="input-group">
                                        <input type="text" id="radius" name="radius" class="form-control" placeholder="In radius">
                                        <span class="input-group-addon"> km</span>
                                    </div>
                                    <input class="form-control" name="userFilter" id="userFilter" type="text" placeholder="User name" style="margin: 5px"><br>
                                    <button type="button" id="btnFilter" class="btn btn-default btn-lg btn-block">Add Filter!</button>
                                </form>
                            </div>
                        </li>
                    </ul>
                <div id="map-canvas"></div>
            </div>
        </div>
        <div class="col-md-3 content">
            <ul class="nav pull-center" role="center">
                <li class="dropdown" id="menuGroup">
                    <a class="dropdown-toggle" data-toggle="dropdown" id="addGroup" style="text-align: center; font-size: 15px">Add Group</a>
                    <div class="dropdown-menu" style="padding:17px;">
                        <form class="form" id="formGroup" style="margin: 5px">
                            <div id="grouplog"></div>
                            <input class="form-control" name="groupName" id="groupname" type="text" placeholder="Group name" style="margin: 5px">
                            <input class="form-control" name="usersList" id="userslist" type="text" placeholder="Add users/ notwork" style="margin: 5px">
                            <input class="form-control" name="eventsList" id="eventslist" type="text" placeholder="Add events/ notwork" style="margin: 5px">

                            <button type="button" id="btnGroup" class="btn btn-default btn-lg btn-block">Add Group!</button>
                        </form>
                    </div>
                </li>
            </ul>

            <div id="groupInfo">
                <%--<button id="jsonGroupBtn">JSON</button>--%>
            </div>
        </div>
    </div>
</div>


</body>
</html>
