$().ready(function() {
    $('#submit_edit').click(function(){


//        var profile = $('#editForm').serialize();
        $.ajax({
            url:'edit_servlet',
            type:'POST',
            data: $('#editForm').serialize(),
            dataType: 'JSON',
            success:function(data){

                var form = $('#editResponse');

                switch (data.name) {
                    case "pass_change":
                        form.empty();
                        form.css("color", "green");
                        form.append("pass change");
                        $('#new_pwd').val('');
                        $('#old_pwd').val('');
                        break;
                    case "name_change":
                        form.empty();
                        form.css("color", "green");
                        form.append("name change");
                        $('#user').val('');
                        $('#new_pwd').val('');
                        $('#old_pwd').val('');
                        break;
                    case "name_and_pass_change":
                        form.empty();
                        form.css("color", "green");
                        form.append("name and pass change");
                        $('#user').val('');
                        $('#new_pwd').val('');
                        $('#old_pwd').val('');
                        break;
                    case "incorrect_pass_data":
                        form.empty();
                        form.css("color", "red");
                        form.append("incorrect data");
                        $('#user').val('');
                        $('#new_pwd').val('');
                        $('#old_pwd').val('');
                        break;
                    default :
                        form.empty();
                        form.css("color", "red");
                        form.append("Server error :(");
                        $('#user').val('');
                        $('#new_pwd').val('');
                        $('#old_pwd').val('');
                        break;
                }

            }
//            error: function(data){
//                alert($('#editForm').serialize());
//            }
        });
        return false;
    });
});