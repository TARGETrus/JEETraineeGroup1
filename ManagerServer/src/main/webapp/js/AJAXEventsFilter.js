$().ready(function() {
    $('#btnFilter').click(function(){
        $('#eventFilterInfo').html('');
        var pinColor = "fec069";
        var pinImage = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_letter&chld=%E2%80%A2|" + pinColor,
            new google.maps.Size(21, 34),
            new google.maps.Point(0,0),
            new google.maps.Point(10, 34));
        var pinShadow = new google.maps.MarkerImage("http://chart.apis.google.com/chart?chst=d_map_pin_shadow",
            new google.maps.Size(40, 37),
            new google.maps.Point(0, 0),
            new google.maps.Point(12, 35));

        $.ajax({
            url:'eventfilterjson',
            type:'POST',
            data: $("#formFilter").serialize(),
            dataType: 'JSON',
            success:function(data){
                    for(i in data){
                        var list = "<li><a href=\"#\">Userlist:</a></li>";
                        for(ii in data[i].userlist){
                            list += ("<li><a  href=\"/users?username=" + data[i].userlist[ii] + "\">" + data[i].userlist[ii] +"</a></li>");
                        };
                        $('#eventFilterInfo').append("" +
                            "<div class=\"btn-group col-sm-12\" style=\"padding-bottom: 10px;\">" +
                            "<button type=\"button\" onclick=\"window.location.href='/events?eventname=" + data[i].eventname + "'\" class=\"btn btn-default pull-left\" style=\"width: 88%;\">" + data[i].eventname +"</button>" +
                            "<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">" +
                            "<span class=\"caret\"></span>" +
                            "<span class=\"sr-only\">Toggle Dropdown</span>" +
                            "</button>" +
                            "<ul class=\"dropdown-menu\" role=\"menu\" style='position: relative;'>" + "<li><a href=\"#\">" + "lat: " + data[i].lat + "<br />" + "lng: " + data[i].lng + "<br />" + "address: " + data[i].address + "</a></li>" + list + "</ul>" + "</div><br />");


                        var marker =+ new google.maps.Marker({
                            position: new google.maps.LatLng(data[i].lat, data[i].lng),
                            map:map,
                            icon: pinImage,
                            shadow: pinShadow,
                            title:data[i].address
                        });



                    }
            }
        });
    });

    $('#btnFilterApply').click(function(){

        $.ajax({
            url:'add_event',
            type:'POST',
            data: eventform,
            dataType: 'JSON',
            success:function(data){

            }
        });
    });
});
