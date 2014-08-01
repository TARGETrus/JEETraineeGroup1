$(document).ready(function() {
    $('#ajaxForm').submit(function(){
        $.ajax({
            url:'search',
            type:'POST',
            dataType: 'json',
            data:$('#ajaxForm').serialize(),
            success:function(date){
                $('#ajaxResponse').html(data);
            }
        });
        return false;
    });
});