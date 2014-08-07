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
                        form.append("pass change");
                        break;
                    case "name_and_pass_change":
                        form.empty();
                        form.append("name and pass change");
                        break;
                    case "incorrect_pass_data":
                        form.empty();
                        form.append("incorrect data");
                        break;
                    default :
                        form.empty();
                        form.append("Server error :(")
                        break;
                }

            },
            error: function(data){
                alert($('#editForm').serialize());
            }
        });
        return false;
    });
});