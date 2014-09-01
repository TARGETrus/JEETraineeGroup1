$().ready(addGroupOnPage() );

function addGroupOnPage() {

    $('#groupInfo').html('');

    if (window.location.href.indexOf("adminpanel") >= 0) {

        $.post('admin_group_json',document.cookie,function(responseText) {

            for(i in responseText){

                $('#groupInfo').append("<p>" +
                    "<div class=\"btn-group col-sm-12\" style=\"padding-bottom: 10px;\">" +
                    "<button type=\"button col-sm-12\" onclick=\"window.location.href='/groups?groupname=" + responseText[i].groupname + "'\" class=\"btn btn-default\" style=\"width: 88%;\">" + responseText[i].groupname +"</button>" +
                    "<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">" +
                    "<span class=\"caret\"></span>" +
                    "<span class=\"sr-only\">Toggle Dropdown</span>" +
                    "</button>" +
                    "<ul class=\"dropdown-menu\" role=\"menu\" style='position: relative;'>" +
                    "<li><a href=\"editGroup.jsp?entityName=" + responseText[i].groupname + "\">Modify group</a></li>" +
                    "<li><a href=\"delete_entity?entityType=group&entityName=" + responseText[i].groupname + "\">Delete group</a></li>" +
                    "</ul></div></p>");

            }

        })

    } else {

        $.post('groupjson',document.cookie,function(responseText) {

            for(i in responseText){

                var list = "<li><a href=\"#\">Userlist:</a></li>";
//                    $('#groupInfo').append("groupname: "+responseText[i].groupname + "<br />" + "user list: " + responseText[i].userlist + "<br />");//.insertAdjacentHTML(responseText[i].groupname + "<br />" + responseText[i].userlist);

                for(ii in responseText[i].userlist){
                    list += ("<li><a href=\"/users?username=" + responseText[i].userlist[ii] + "\">" + responseText[i].userlist[ii] +"</a></li>");
                };

                $('#groupInfo').append("<p>" +
                    "<div class=\"btn-group col-sm-12\" style=\"padding-bottom: 10px;\">" +
                    "<button type=\"button col-sm-12\" onclick=\"window.location.href='/groups?groupname=" + responseText[i].groupname + "'\" class=\"btn btn-default\" style=\"width: 88%;\">" + responseText[i].groupname +"</button>" +
                    "<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">" +
                    "<span class=\"caret\"></span>" +
                    "<span class=\"sr-only\">Toggle Dropdown</span>" +
                    "</button>" +
                    "<ul class=\"dropdown-menu\" role=\"menu\" style='position: relative;'>" + list + "</ul>" + "</div></p>");

            }

        })

    }

}