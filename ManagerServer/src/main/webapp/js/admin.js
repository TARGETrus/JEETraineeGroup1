$().ready(function() {
    $('#apply_users').click(function(){
        $.ajax({
            url:'edit_servlet',
            type:'POST',
            data: $('#editForm').serialize(),
            dataType: 'JSON',
            success:function(data){

            }
//            error: function(data){
//                alert($('#editForm').serialize());
//            }
        });
    });
    $('#apply_events').click(function(){
        $.ajax({
            url:'edit_servlet',
            type:'POST',
            data: $('#editForm').serialize(),
            dataType: 'JSON',
            success:function(data){

            }
//            error: function(data){
//                alert($('#editForm').serialize());
//            }
        });
    });
    $('#apply_').click(function(){
        $.ajax({
            url:'edit_servlet',
            type:'POST',
            data: $('#editForm').serialize(),
            dataType: 'JSON',
            success:function(data){

            }
//            error: function(data){
//                alert($('#editForm').serialize());
//            }
        });
    });
});