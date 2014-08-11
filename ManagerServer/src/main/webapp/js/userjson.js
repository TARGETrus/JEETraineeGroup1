$().ready(function() {
    $('#jsonBtn').click(function(){
        var groupform = $('#formGroup').serialize();
        $.post('jsongen',document.cookie,function(responseText) {
            $('#json').text(responseText);
        });
    });
});