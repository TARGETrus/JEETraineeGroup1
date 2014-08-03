$().ready(function() {
    $('#submit').click(function(){

        var userfrom = $('#ajaxForm').serialize();
         $.ajax({
            url:'search',
            type:'POST',
             data: userfrom,
             dataType: 'JSON',
            success:function(data){

                var form = $('#ajaxResponse');

                switch (data.name) {
                    case "exist":
                        form.empty();
                        form.append("exist");
                        break;
                    case "not_exist":
                        form.empty();
                        form.css("color", "green");
                        form.append("name OK")
                        break;
                    case "invalid_data":
                        form.empty();
                        form.append("invalid data")
                        break;
                    case "pss_or_name_incorrect":
                        form.empty();
                        form.append("Password or username don't match :(");
                        break;
                    default :
                        form.empty();
                        form.append("Server error :(")
                        break;
                }

            }
        });
        return false;
    });
});