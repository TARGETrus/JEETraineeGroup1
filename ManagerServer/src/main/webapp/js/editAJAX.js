$().ready(function() {

    // Edit User
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

    // Edit User (Admin)
    $('#submit_edit_user').click(function(){

        $.ajax({
            url:'edit_user_servlet',
            type:'POST',
            data: $('#editForm').serialize(),
            dataType: 'JSON',
            success:function(data){

                var form = $('#editResponse');

                switch (data.name) {
                    case "change successful!":
                        form.empty();
                        form.css("color", "green");
                        form.append("change successful!");
                        $('#user_name').val(data.newName);
                        $('#userTitle').text("Edit " + data.newName + " profile");
                        $('#user').val('');
                        $('#new_pwd').val('');
                        $('#old_pwd').val('');
                        $('#role').val('');
                        break;
                    case "invalid_data":
                        form.empty();
                        form.css("color", "red");
                        form.append("incorrect data");
                        $('#user').val('');
                        $('#new_pwd').val('');
                        $('#old_pwd').val('');
                        $('#role').val('');
                        break;
                    default :
                        form.empty();
                        form.css("color", "red");
                        form.append("Server error :(");
                        $('#user').val('');
                        $('#new_pwd').val('');
                        $('#old_pwd').val('');
                        $('#role').val('');
                        break;
                }

            }

        });

        return false;

    });

    // Edit Event (Admin)
    $('#submit_edit_event').click(function(){

        $.ajax({
            url:'edit_event_servlet',
            type:'POST',
            data: $('#editForm').serialize(),
            dataType: 'JSON',
            success:function(data){

                var form = $('#editResponse');

                switch (data.name) {
                    case "change successful!":
                        form.empty();
                        form.css("color", "green");
                        form.append("change successful!");
                        $('#event_name').val(data.newName);
                        $('#eventTitle').text("Edit " + data.newName + " event");
                        $('#event').val('');
                        $('#coordinates').val('');
                        $('#latitude').val('');
                        $('#longitude').val('');
                        $('#date').val('');
                        $('#event_admin').val('');
                        break;
                    case "invalid_data":
                        form.empty();
                        form.css("color", "red");
                        form.append("incorrect data");
                        $('#event').val('');
                        $('#coordinates').val('');
                        $('#latitude').val('');
                        $('#longitude').val('');
                        $('#date').val('');
                        $('#event_admin').val('');
                        break;
                    default :
                        form.empty();
                        form.css("color", "red");
                        form.append("Server error :(");
                        $('#event').val('');
                        $('#coordinates').val('');
                        $('#latitude').val('');
                        $('#longitude').val('');
                        $('#date').val('');
                        $('#event_admin').val('');
                        break;
                }

            }

        });

        return false;

    });

    // Edit Group (Admin)
    $('#submit_edit_group').click(function(){

        $.ajax({
            url:'edit_group_servlet',
            type:'POST',
            data: $('#editForm').serialize(),
            dataType: 'JSON',
            success:function(data){

                var form = $('#editResponse');

                switch (data.name) {
                    case "change successful!":
                        form.empty();
                        form.css("color", "green");
                        form.append("change successful!");
                        $('#group_name').val(data.newName);
                        $('#groupTitle').text("Edit " + data.newName + " group");
                        $('#group').val('');
                        $('#group_admin').val('');
                        break;
                    case "invalid_data":
                        form.empty();
                        form.css("color", "red");
                        form.append("incorrect data");
                        $('#group').val('');
                        $('#group_admin').val('');
                        break;
                    default :
                        form.empty();
                        form.css("color", "red");
                        form.append("Server error :(");
                        $('#group').val('');
                        $('#group_admin').val('');
                        break;
                }

            }

        });

        return false;

    });

});