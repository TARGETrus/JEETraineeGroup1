$().ready(addCollectionsOnPage());

function addCollectionsOnPage() {

    if (window.location.href.indexOf("editGroup.jsp") >= 0 || window.location.href.indexOf("editEvent.jsp")) {

        $('#userCollection').html('');

        $.post('userjson',document.cookie,function(responseText) {

            var userColl = "<p>" +
                "<select name=\"user_collection\">" +
                "<option value=\"\" selected>pick user</option>";

            for(i in responseText){
                userColl += "<option value=\"" + responseText[i].username + "\">" + responseText[i].username + "</option>";
            }

            userColl += "</select></p>";

            $('#userCollection').append(userColl);

        });

    }

    if (window.location.href.indexOf("editUser.jsp") >= 0 || window.location.href.indexOf("editGroup.jsp")) {

        $('#eventCollection').html('');

        $.post('admin_event_json',document.cookie,function(responseText) {

            var eventColl = "<p>" +
                "<select name=\"event_collection\">" +
                "<option value=\"\" selected>pick event</option>";

            for(i in responseText){
                eventColl += "<option value=\"" + responseText[i].eventname + "\">" + responseText[i].eventname + "</option>";
            }

            eventColl += "</select></p>";

            $('#eventCollection').append(eventColl);

        });

    }

    if (window.location.href.indexOf("editUser.jsp") >= 0 || window.location.href.indexOf("editEvent.jsp")) {

        $('#groupCollection').html('');

        $.post('admin_group_json',document.cookie,function(responseText) {

            var groupColl = "<p>" +
                "<select name=\"group_collection\">" +
                "<option value=\"\" selected>pick group</option>";

            for(i in responseText){
                groupColl += "<option value=\"" + responseText[i].groupname + "\">" + responseText[i].groupname + "</option>";
            }

            groupColl += "</select></p>";

            $('#groupCollection').append(groupColl);

        });

    }

}
