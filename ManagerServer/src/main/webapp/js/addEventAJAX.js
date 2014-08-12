$().ready(function() {
    $('#btnEvent').click(function(){
        var address = $('#coord').val();
        var form = $('#eventlog');
        $.ajax({
            url: "http://maps.googleapis.com/maps/api/geocode/json?address="+address+"&sensor=false",
            type: "POST",
            success: function(res){
                var lat = res.results[0].geometry.location.lat;
                var lng = res.results[0].geometry.location.lng;

                alert(address);
                var coord = {"lat": lat, "lng":lng};
                if(lat != 0 && lng != 0){
                    var eventform = $('#formEvent').find("#eventname, #date, #coord").serialize() + '&' + $.param({"lat": lat, "lng":lng, "address": address}) + '&' + document.cookie;

//                    alert(eventform);
                    $.ajax({
                        url:'add_event',
                        type:'POST',
                        data: eventform,
                        dataType: 'JSON',
                        success:function(data){

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
            }else {
                form.empty();
        form.css("color", "red");
        form.append("incorrct date :(")
    }
}
});
});
});