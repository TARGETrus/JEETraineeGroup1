/*$().ready(function(latitude, longitude) {
    alert("latitude = " + latitude + " longitude = " + longitude);
    $('#map-canvas').click(function(){
        $.ajax({
            url: "http://maps.googleapis.com/maps/api/geocode/json?latlng=  " + latitude + "," + longitude + "&sensor=false",
            type: "POST",
            success: function(res) {
                var address = res.results[4].type[0].formatted_address;
                alert("!!! = " + address);
                var eventform = $('#formEvent').find("#eventname, #date, #coord").serialize() + '&' + $.param({"lat": latitude, "lng":longitude, "address": address});

                $.ajax({
                    url:'add_event',
                    type:'POST',
                    data: eventform,
                    dataType: 'JSON',
                    success:function(data){
                        $("#coord").text(address);

                        switch (data.name) {
                            case "add_event":
                                form.empty();
                                form.css("color", "green");
                                form.append("event add!");
                                break;
                            case "error":
                                form.empty();
                                form.css("color", "red");
                                form.append("error :(");
                                break;
                            default :
                                form.empty();
                                form.append("Server error :(")
                                break;
                        }
                    }
                });
            }
        });
    })
});*/