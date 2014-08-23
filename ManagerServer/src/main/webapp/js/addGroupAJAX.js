$().ready(function() {
    $('#btnGroup').click(function(){

        var groupform = $('#formGroup').serialize() + '&' + document.cookie;
        $.ajax({
            url:'add_group',
            type:'POST',
            data: groupform,
            dataType: 'JSON',
            success:function(data){

                var form = $('#grouplog');

                switch (data.name) {
                    case "add_group":
                        form.empty();
                        form.css("color", "green");
                        form.append("group add!");
                        addGroupOnPage();
                        break;

                    case "error":
                        form.empty();
                        form.css("color", "red");
                        form.append("error :(");
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