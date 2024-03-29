$().ready(addEventOnPage());

function addEventOnPage() {

    $('#eventInfo').html('');

    if (window.location.href.indexOf("adminpanel") >= 0) {

        $.post('admin_event_json',document.cookie,function(responseText) {

            for(i in responseText){

                $('#eventInfo').append("" +
                    "<div class=\"btn-group col-sm-12\" style=\"padding-bottom: 10px;\">" +
                    "<button type=\"button\" onclick=\"window.location.href='/events?eventname=" + responseText[i].eventname + "'\" class=\"btn btn-default pull-left\" style=\"width: 88%;\">" + responseText[i].eventname +"</button>" +
                    "<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">" +
                    "<span class=\"caret\"></span>" +
                    "<span class=\"sr-only\">Toggle Dropdown</span>" +
                    "</button>" +
                    "<ul class=\"dropdown-menu\" role=\"menu\" style='position: relative;'>" +
                    "<li><a href=\"editEvent.jsp?entityName=" + responseText[i].eventname + "\">Modify event</a></li>" +
                    "<li><a href=\"delete_entity?entityType=event&entityName=" + responseText[i].eventname + "\">Delete event</a></li>" +
                    "</ul></div></p>");

            }

        })

    } else {

        $.post('eventjson',document.cookie,function(responseText) {

            for(i in responseText){

                var list = "<li><a href=\"#\">Userlist:</a></li>";
//                    $('#groupInfo').append("groupname: "+responseText[i].groupname + "<br />" + "user list: " + responseText[i].userlist + "<br />");//.insertAdjacentHTML(responseText[i].groupname + "<br />" + responseText[i].userlist);

                for(ii in responseText[i].userlist){
                    list += ("<li><a  href=\"/users?username=" + responseText[i].userlist[ii] + "\">" + responseText[i].userlist[ii] +"</a></li>");
                };

                $('#eventInfo').append("" +
                    "<div class=\"btn-group col-sm-12\" style=\"padding-bottom: 10px;\">" +
                    "<button type=\"button\" onclick=\"window.location.href='/events?eventname=" + responseText[i].eventname + "'\" class=\"btn btn-default pull-left\" style=\"width: 88%;\">" + responseText[i].eventname +"</button>" +
                    "<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">" +
                    "<span class=\"caret\"></span>" +
                    "<span class=\"sr-only\">Toggle Dropdown</span>" +
                    "</button>" +
                    "<ul class=\"dropdown-menu\" role=\"menu\" style='position: relative;'>" + "<li><a href=\"#\">" + "lat: " + responseText[i].lat + "<br />" + "lng: " + responseText[i].lng + "<br />" + "address: " + responseText[i].address + "</a></li>" + list + "</ul>" + "</div><br />");

            }

        })

    }

}