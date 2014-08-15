$().ready(addFiltersOnPage());


function addFiltersOnPage() {
    $('#eventFilter').empty();
    $('#point').empty();
    $('#radius').empty();
    $('#userFilter').empty();
//    $('#jsonEventBtn').click(function(){
    $.post('addfilter',"flag=apply&" + document.cookie,function(responseText) {
        for(i in responseText){


        }
    });
}


function saveFilterFromPage(){
    var address = $("#point").val();
    $.ajax({
        url: "http://maps.googleapis.com/maps/api/geocode/json?address="+address+"&sensor=false",
        type: "POST",
        success: function(res){
            if(res.status == "OK")
            {
                var lat = res.results[0].geometry.location.lat;
                var lng = res.results[0].geometry.location.lng;
            }else{
                var lat = latitude;
                var lng = longitude;
            }
            var coord = {"lat": lat, "lng":lng};
            $.post('addfilter',"flag=save&" + document.cookie + "&" + $("#formFilter").serialize() + "&" + $.param({"lat": lat, "lng":lng}),function(responseText) {
                var status = responseText.status;
                if(status == "OK"){
                    $("#filterlog").css("color", "green");
                    $("#filterlog").append("Filter Saved!");
                }else{
                    $("#filterlog").css("color", "red");
                    $("#filterlog").append("Error :(");
                }

            });
        }
    });
}