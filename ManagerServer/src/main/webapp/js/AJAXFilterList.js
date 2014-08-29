$().ready(addFiltersOnPage());


function addFiltersOnPage() {
    $('#eventFilter').empty();
    $('#point').empty();
    $('#radius').empty();
    $('#userFilter').empty();
//    $('#jsonEventBtn').click(function(){
    $.post('addfilter',"flag=add&" + document.cookie,function(resp) {
        for(i in resp){
            $("#filterselector").append(
                "<option onclick='fillFilterForm("+resp[i].filtername+")'>" + resp[i].filtername + "</option>"
            )


        }
    });
}


function fillFilterForm(name) {
    $('#eventFilter').empty();
    $('#point').empty();
    $('#radius').empty();
    $('#userFilter').empty();
//    $('#jsonEventBtn').click(function(){
    $.post('findfilter',"filtername=" + name + "&" + document.cookie,function(resp) {

            $('#eventFilter').val(resp.eventname);
            $('#point').val(resp.point);
            $('#radius').val(resp.radius);
            $('#userFilter').val(resp.username);
            latitude = resp.lat;
            longitude = resp.lng;


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