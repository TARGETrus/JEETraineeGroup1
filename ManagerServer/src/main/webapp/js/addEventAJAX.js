$().ready(function() {
    $('#btnEvent').click(function(){
        var address = $('#coord').val();
        $.ajax({
            url: "http://maps.googleapis.com/maps/api/geocode/json?address="+address+"&sensor=false",
            type: "POST",
            success: function(res){
                var lat = res.results[0].geometry.location.lat;
                var lng = res.results[0].geometry.location.lng;
                if(lat != 0 && lng != 0){
                    var eventform = $('#formEvent').serialize();
                    $.ajax({
                        url:'add_event',
                        type:'POST',
                        data: eventform,
                        dataType: 'JSON',
                        success:function(data){

                            var form = $('#');

                            switch (data.name) {
                                case "exist":
                                    form.empty();
                                    form.append("exist");
                                    break;
                                default :
                                    form.empty();
                                    form.append("Server error :(")
                                    break;
                            }

                        }
                    });
                }else {

                }
            }
        });
    });
});