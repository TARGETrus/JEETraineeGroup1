$().ready(function() {

    $('#btnUser').click(function(){

        var userform = $('#formUser').serialize() + '&' + document.cookie;
        $.ajax({
            url:'add_user',
            type:'POST',
            data: userform,
            dataType: 'JSON',
            success:function(data){

                var form = $('#userlog');

                switch (data.name) {
                    case "not_exist":
                        form.empty();
                        form.css("color", "green");
                        form.append("user add!");
                        addUserOnPage();
                        break;

                    case "exist":
                        form.empty();
                        form.css("color", "red");
                        form.append("exist :(");
                        break;

                    case "invalid_data":
                        form.empty();
                        form.css("color", "red");
                        form.append("invalid_data :(");
                        break;

                    default :
                        form.empty();
                        form.append("Server error :(")
                        ;

                }

            }

        });

    });

});