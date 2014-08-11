$().ready(function() {
    $('#jsonGroupBtn').click(function(){
        $.post('groupjson',document.cookie,function(responseText) {
            for(i in responseText){
                $('#groupInfo').append("groupname: "+responseText[i].groupname + "<br />" + "user list: " + responseText[i].userlist + "<br />");//.insertAdjacentHTML(responseText[i].groupname + "<br />" + responseText[i].userlist);
            }
        });
    });
});