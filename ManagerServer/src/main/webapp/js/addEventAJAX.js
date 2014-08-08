$().ready(function() {
    $('#btnEvent').click(function(){
        var address = $('#coord').val();
        $.ajax({
            url: "http://maps.googleapis.com/maps/api/geocode/json?address="+address+"&sensor=false",
            type: "POST",
            success: function(res){
                var lat = res.results[0].geometry.location.lat;
                var lng = res.results[0].geometry.location.lng;
                var coord = {"lat": lat, "lng":lng};
                if(lat != 0 && lng != 0){
                    var eventform = $('#formEvent').find("#eventname, #date").serialize() + '&' + $.param({"lat": lat, "lng":lng});

                    alert(eventform);
                    $.ajax({
                        url:'add_event',
                        type:'POST',
                        data: eventform,
                        dataType: 'JSON',
                        success:function(data){

                            var form = $('#eventlog');

                            switch (data.name) {
                                case "add_event":
                                    form.empty();
                                    form.append("event add!");
                                    break;
                            switch (data.name) {
                                case "add_event":
                                     form.empty();
                                     form.append("event add!");
                                        break;
                            default :
                                form.empty();
                                form.append("Server error :(")
                                            break;
                                }
                            }
                        }
                    });
                }else {
                    alert("error");
                }
            }
        });
    });
});