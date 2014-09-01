$().ready(function() {
    $('#btnFilterGroup').click(function(){
        $('#groupFilterInfo').html('');

        var data = $('#formFilterGroup').serialize() + '&' + document.cookie;

        $.post('filtergroup',data,function(responseText) {
            $('#groupFilterInfo').empty();
            for(i in responseText){
                var list = "<li><a href=\"#\">Userlist:</a></li>";
//                $('#groupInfo').append("groupname: "+responseText[i].groupname + "<br />" + "user list: " + responseText[i].userlist + "<br />");//.insertAdjacentHTML(responseText[i].groupname + "<br />" + responseText[i].userlist);
                for(ii in responseText[i].userlist){
                    list += ("<li><a href=\"/users?username=" + responseText[i].userlist[ii] + "\">" + responseText[i].userlist[ii] +"</a></li>");
                };
                $('#groupFilterInfo').append("<p>" +
                    "<div class=\"btn-group col-sm-12\" style=\"padding-bottom: 10px;\">" +
                    "<button type=\"button col-sm-12\" onclick=\"window.location.href='/groups?groupname=" + responseText[i].groupname + "'\" class=\"btn btn-default\" style=\"width: 88%;\">" + responseText[i].groupname +"</button>" +
                    "<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">" +
                    "<span class=\"caret\"></span>" +
                    "<span class=\"sr-only\">Toggle Dropdown</span>" +
                    "</button>" +
                    "<ul class=\"dropdown-menu\" role=\"menu\" style='position: relative;'>" + list + "</ul>" + "</div></p>");
            }
        });
    });
});
