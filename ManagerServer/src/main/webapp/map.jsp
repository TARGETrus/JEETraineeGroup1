<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Simple click event</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">
    <meta charset="utf-8">
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

            /*var marker = new google.maps.Marker({
                position: map.getCenter(),
                map: map,
                title: 'Click to zoom'
            });

            var markers = new google.maps.Marker({
                position: new google.maps.LatLng(59.934280,30.335099),
                map: map,
                title:"Hello World!"
            });*/

            <%
                for(int i = 0; i < latitude.size(); i++) {
            %>
                var marker = new google.maps.Marker({
                    position: new google.maps.LatLng(<%= latitude.get(i)%>,<%= longitude.get(i)%>),
                    map: map,
                    title:"Hello World!"
                });
           <%
            }
           %>

            /*google.maps.event.addListener(map, 'center_changed', function() {
                // 3 seconds after the center of the map has changed, pan back to the
                // marker.
                window.setTimeout(function() {
                    map.panTo(markers.getPosition());
                }, 3000);
            });

            google.maps.event.addListener(marker, 'click', function() {
                map.setZoom(15);
                map.setCenter(marker.getPosition());
            });*/
        }

        google.maps.event.addDomListener(window, 'load', initialize);

    </script>
</head>
<body>
<div id="map-canvas"></div>
</body>
</html>