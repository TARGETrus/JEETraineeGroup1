$().ready(addUserOnPage());

function addUserOnPage() {

    $('#userInfo').html('');

    $.post('userjson',document.cookie,function(responseText) {

        for(i in responseText){

            $('#userInfo').append("<p>" +
                "<div class=\"btn-group col-sm-12\" style=\"padding-bottom: 10px;\">" +
                "<button type=\"button col-sm-12\" onclick=\"window.location.href='/users?username=" + responseText[i].username + "'\" class=\"btn btn-default\" style=\"width: 88%;\">" + responseText[i].username +"</button>" +
                "<button type=\"button\" class=\"btn btn-default dropdown-toggle\" data-toggle=\"dropdown\">" +
                "<input type=\"hidden\" name=\"usrName\" id=\"usrName\" value=\"" + responseText[i].username + "\">" +
                "<span class=\"caret\"></span>" +
                "<span class=\"sr-only\">Toggle Dropdown</span>" +
                "</button>" +
                "<ul class=\"dropdown-menu\" role=\"menu\" style='position: relative;'>" +
                "<li><a href=\"editUser.jsp?entityName=" + responseText[i].username + "\">Modify user</a></li>" +
                "<li><a href=\"delete_entity?entityType=user&entityName=" + responseText[i].username + "\">Delete user</a></li>" +
                "</ul></div></p>");

        }

    });

}
