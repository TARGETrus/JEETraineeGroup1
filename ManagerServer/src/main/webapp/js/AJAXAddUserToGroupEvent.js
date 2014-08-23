$().ready(function() {
    $('#groupAdd').click(AJAXGroupAddUser);
});

$().ready(function() {
    $('#eventAdd').click(AJAXEventAddUser);
});

var qs = (function(a) {
    if (a == "") return {};
    var b = {};
    for (var i = 0; i < a.length; ++i)
    {
        var p=a[i].split('=');
        if (p.length != 2) continue;
        b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));
    }
    return b;
})(window.location.search.substr(1).split('&'));

function AJAXEventAddUser(){


$.post('follow',"flag=event&" + document.cookie + "&" + "event="+ qs["eventname"],function(resp) {
        if(resp.message == "OK"){
            $("#").append("User add!");
        }else{
            $("#").append("Error :(");

        }
    });
}
function AJAXGroupAddUser() {
    var group = qs["groupname"];
    $.post('follow',"flag=group&" + document.cookie + "&" + "group="+ group,function(resp) {
        if(resp.message == "OK"){
            $("#").append("User add!");
        }else{
            $("#").append("Error :(");

        }
    });
}