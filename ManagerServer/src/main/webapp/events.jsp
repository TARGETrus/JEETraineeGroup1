<%@page import="model.User"%>
<%@page import="model.Group"%>
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
    <title>EventManagerMainPage</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/dopstyle.css" rel="stylesheet" media="screen">
    <script src="js/bootstrap.min.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="../../assets/js/html5shiv.js"></script>
    <script src="../../assets/js/respond.min.js"></script>
    <![endif]-->
    <script language="javascript">

        function fillMembers(){
            var groups = document.getElementById("groups");
            var selectedValue = groups.options[groups.selectedIndex].value;

            document.getElementById("gMembers").innerHTML = selectedValue;
        }

    </script>

    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">

    <style>
        html, body, #map-canvas {
            height: 100%;
            margin: 0px;
            padding: 0px
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
        function initialize() {
            var mapOptions = {
                zoom: 2,
                center: new google.maps.LatLng(40, 20)
            };

            var map = new google.maps.Map(document.getElementById('map-canvas'),
                    mapOptions);

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

<div class="container">
    <div class="row">
        <div class="col-md-12 head-block">
            <h1 align="center">ManagerEvent</h1>
            <form action="LogoutServlet"  method="post">
                <p style="text-align: right;">
                    <label>Hi <%=userName %>, Login was successful.</label>
                    <input type="button" value="Edit" onclick="window.location.href='/edit.jsp'">
                    <button type="submit">Logout</button>
                </p>
            </form>

        </div>
        <div class="col-md-3 content">
            <h2>Events</h2>
            
        </div>
        <div class="col-md-6">
            <div class=".col-sm-12 top-menu">
                Filters
            </div>
            <div class="col-md-12 map_content">
                <%--<jsp:include page="map.jsp"/>--%>



                    <div id="map-canvas"></div>
            </div>
        </div>
        <div class="col-md-3 content">
            <h2>Groups</h2>
             <p style="text-align: center;">
                <select multiple="false" id="groups" onchange="fillMembers();">
                    <%

                        java.util.Iterator<Group> itr = (user!=null) ?
                                user.getGroups().iterator():null;
                        while(itr!=null && itr.hasNext()) {
                            Group group = itr.next();
                            String gname = group.getGroupName();

                            String nameList= "";
                            java.util.Iterator<User> gitr = group.getUsers().iterator();
                            while(gitr.hasNext()) {
                                User next = gitr.next();
                                nameList = nameList + next.getUserName() + "<br>";
                            }

                    %>
                    <option value="<%=nameList%>"><%=gname%></option>
                    <%
                        }
                    %>
                </select>
            </p>
            <p style="text-align: center;" id="gMembers">outmembers</p>
        </div>
    </div>
</div>


</body>
</html>
