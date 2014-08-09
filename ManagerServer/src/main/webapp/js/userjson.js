$().ready(function() {
    $('#jsonBtn').click(function(){
        var groupform = $('#formGroup').serialize();
        $.ajax({
            url:'jsongen',
            type:'POST',
            data: document.cookie,
            dataType: 'JSON',
            success:function(data){
                var form = $('#json');
                form.empty();
                form.css("color", "green");
                form.append(data);
            }

        });
    });
});