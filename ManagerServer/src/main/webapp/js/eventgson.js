$().ready(function() {
    $('#jsonEventBtn').click(function(){
        $.post('eventjson',document.cookie,function(responseText) {
            for(i in responseText){
                $('#eventInfo').append("eventname" + responseText[i].eventname + "<br />" + "user list" + responseText[i].userlist + "<br />" + "lat: " + responseText[i].lat + "<br />" + "lng: " + responseText[i].lng + "<br />");//.insertAdjacentHTML(responseText[i].groupname + "<br />" + responseText[i].userlist);
            }
        });
    });
});