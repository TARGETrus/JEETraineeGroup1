$().ready(function() {
    $('#submit').click(function(){
        '#submit'.disabled = true;
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
                        form.append("name OK")
                        break;
                    case "invalid_data":
                        form.empty();
                        form.append("invalid data")
                        break;
                    default :
                        break;
                }

            }
        });
        return false;
    });
});