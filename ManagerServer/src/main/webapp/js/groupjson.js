$().ready(function() {
//    $('#jsonGroupBtn').click(function(){
        $.post('groupjson',document.cookie,function(responseText) {
            for(i in responseText){
                var list = "<li><a href=\"#\">Userlist:</a></li>";
//                $('#groupInfo').append("groupname: "+responseText[i].groupname + "<br />" + "user list: " + responseText[i].userlist + "<br />");//.insertAdjacentHTML(responseText[i].groupname + "<br />" + responseText[i].userlist);
                for(ii in responseText[i].userlist){
                    list += ("<li><a href=\"/users?username=" + responseText[i].userlist[ii] + "\">" + responseText[i].userlist[ii] +"</a></li>");
                };
                $('#groupInfo').append("<p>" +
                    "<div class=\"btn-group\">" +
                    "<button type=\"button\" onclick=\"window.location.href='/groups?groupname=" + responseText[i].groupname + "'\" class=\"btn btn-default\">" + responseText[i].groupname +"</button>" +
                    "<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">" +
                    "<span class=\"caret\"></span>" +
                    "<span class=\"sr-only\">Toggle Dropdown</span>" +
                    "</button>" +
                    "<ul class=\"dropdown-menu\" role=\"menu\">" + list + "</ul>" + "</div></p>");
            }
        });
//    });
});
