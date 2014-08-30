<%@page import="model.User"%>
<%@page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="UTF-8"%>


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
    <%--<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">--%>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <%--<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">--%>
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">
    <%--<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>--%>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
<%--<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>--%>
    <script src="js/groupjson.js"></script>
    <script src="js/addEventAJAX.js"></script>
    <script src="js/addGroupAJAX.js"></script>
    <script src="js/eventgson.js"></script>
    <script src="js/AJAXEventsFilter.js"></script>
    <script src="js/AJAXGroupFilter.js"></script>
    <script src="js/AJAXFilterList.js"></script>
    <link href="css/dopstyle.css" rel="stylesheet" media="screen">
    <link href="css/style.css" rel="stylesheet" media="screen">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <style>
        html, body, #map-canvas {
            height: 470px;
            padding: 5px;
        }
    </style>
    <script src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAZR0VB_Fk4ZOdDmAwhda8RZsvkwCDFcps&sensor=FALSE"></script>
    <script>
        var latitude;
        var longitude;
        var map;
        var markers = [];


        function initialize() {
            var mapOptions = {
                zoom: 10,
                center: new google.maps.LatLng(59.944126, 30.246234)
            };

            map = new google.maps.Map(document.getElementById('map-canvas'),
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

            google.maps.event.addListener(map, "rightclick", function (event) {
                latitude = event.latLng.lat();
                longitude = event.latLng.lng();
                $.ajax({
                    url: "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + latitude + "," + longitude + "&sensor=false",
                    type: "POST",
                    success: function (res) {
                        var address = res.results[0].formatted_address;

                        $("#addFilter").click();
                        $(".form-control").val("")
                        $("#point").val(address);


                    }
                });
            });


            <!-- add markers-->



            $.post('eventjson',document.cookie,function(responseText) {
                var pinColor = "69fe75";
                var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
                        new google.maps.Size(21, 34),
                        new google.maps.Point(0,0),
                        new google.maps.Point(10, 34));
                var pinShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
                        new google.maps.Size(40, 37),
                        new google.maps.Point(0, 0),
                        new google.maps.Point(12, 35));
                for(i in responseText){
                    var marker = new google.maps.Marker({
                        position: new google.maps.LatLng(responseText[i].lat,responseText[i].lng),
                        map: map,
                        icon: pinImage,
                        shadow: pinShadow,
                        title:responseText[i].address
                    });
                    markers.push(marker);
                }
            });

        }

        function setAllMap(map) {
            for (var i = 0; i < markers.length; i++) {
                markers[i].setMap(map);
            }
        }


        function clearMarkers() {
            setAllMap(null);
        }

        function showMarkers() {
            setAllMap(map);
        }

        function deleteMarkers() {
            clearMarkers();
            markers = [];
        }


        google.maps.event.addDomListener(window, 'load', initialize);
    </script>
</head>
<body>


<div class="container">
    <div class="row">
        <div class="col-sm-12 head-block" style="background: url('img/logo_head.png') no-repeat; background-color: #F7F7F7;">
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
        <div class="col-sm-3 content" style="overflow:scroll">
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
            <label>My events:</label>
            <div id="eventInfo">
                <%--<br><button id="jsonEventBtn">JSON</button></br>--%>
            </div>
            <hr>
            <label>Filtered events:</label>
            <div id="eventFilterInfo">

                <%--<br><button id="jsonEventBtn">JSON</button></br>--%>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="col-sm-12 map_content">
                <ul class="nav pull-center" role="center">
                    <li class="dropdown" id="filterMenu">
                        <a class="dropdown-toggle" data-toggle="dropdown" id="addFilter" style="text-align: center; font-size: 15px">Add Event</a>
                        <div class="dropdown-menu" style="padding:17px;">
                            <form class="form pull-left" id="formFilter" style="margin: 5px">
                                <div id="filterlog"></div>

                                <select onchange="fillFilterForm(this.value)" class="form-control" id="filterselector" style="margin: 5px">
                                    <option  value="" disabled="disabled" selected="selected">Please select your filter</option>
                                </select>
                                <input class="form-control" name="eventFilter" id="eventFilter" type="text" placeholder="Event name" style="margin: 5px">
                                <input class="form-control" name="point" id="point" type="text" placeholder="Starting point" style="margin: 5px">
                                <div class="input-group" style="margin: 5px">
                                    <input type="text" id="radius" name="radius" class="form-control" placeholder="In radius">
                                    <span class="input-group-addon"> km</span>
                                </div>
                                <input class="form-control" name="userFilter" id="userFilter" type="text" placeholder="User name" style="margin: 5px"><br>
                                <div class="btn-group" style="float: right;">
                                    <button type="button" id="btnFilterApply" class="btn btn-default">Apply</button>
                                    <button type="button" id="btnFilterSave" onclick="saveFilterFromPage()" class="btn btn-default">Save</button>
                                    <%--<button type="button" id="btnFilterDel" class="btn btn-default">Del</button>--%>
                                </div>
                            </form>
                        </div>
                    </li>
                </ul>
                <div id="map-canvas"></div>
            </div>
        </div>
        <div class="col-sm-3 content" style="overflow:scroll">
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

            <label>My groups:</label>
            <div id="groupInfo">
                <%--<button id="jsonGroupBtn">JSON</button>--%>
            </div>
            <hr>
            <label>Find groups:</label>
            <ul class="nav pull-center" role="center">
                <li class="dropdown" id="filterGroup">
                    <a class="dropdown-toggle" data-toggle="dropdown" id="addFilterGroup" style="text-align: center; font-size: 15px">Find Group</a>
                    <div class="dropdown-menu" style="padding:17px;">
                        <form class="form" id="formFilterGroup" style="margin: 5px">
                            <input class="form-control" name="groupName" id="filtergroupname" type="text" placeholder="Group name" style="margin: 5px">
                            <button type="button" id="btnFilterGroup" class="btn btn-default btn-lg btn-block">Find Group!</button>
                        </form>
                    </div>
                </li>
            </ul>
            <div id="groupFilterInfo">
                <%--<button id="jsonGroupBtn">JSON</button>--%>
            </div>
        </div>
    </div>
</div>

<div id="messageEventAdded">Event added</div>
<div id="messageError">Error</div>
<div id="messageServerError">Server error</div>

</body>
</html>
