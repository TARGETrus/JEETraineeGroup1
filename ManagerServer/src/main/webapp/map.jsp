<%--
  Created by IntelliJ IDEA.
  User: Хвёдор
  Date: 26.07.14
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Карта</title>
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />

    <script type="text/javascript"
            src="http://maps.googleapis.com/maps/api/js?key=AIzaSyAZR0VB_Fk4ZOdDmAwhda8RZsvkwCDFcps&sensor=SET_TO_TRUE_OR_FALSE">
    </script>
    <script type="text/javascript">
        function initialize() {
            var mapOptions = {
                center: new google.maps.LatLng(59.934280,30.335099),
                zoom: 8,
                mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            var map = new google.maps.Map(document.getElementById("map_canvas"),
                    mapOptions);
            var marker = new google.maps.Marker({
                position: new google.maps.LatLng(59.934280,30.335099),
                map: map,
                title:"Hello World!"
            });
        }
    </script>
</head>
<body>
    <div id="map_canvas" style="width:100%; height:100%"></div>
</body>
</html>
