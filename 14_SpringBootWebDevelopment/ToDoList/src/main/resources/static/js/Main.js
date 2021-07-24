$(function()){
    const newCasse = function(data){
        var casseCode = '<a href="#" class="casse-link" data-id="' +
        data.id + '">' + data.name + '</a><br>';
        $('#casse-list')
            .append('<div>' + casseCode + '</div>');
    });

    $.get('/Cases/', function(response){
        for(i in response){
            newCase(response[i]);
        }
    });

    //Открываем форму добавления формы
    $('#show-add-casse-form').click(function(){
        $('#casse-form').css('display', 'flex');
    });

    //Закрываем форму(нажатие вне формы)
    $('#casse-form').click(function(event){
        if(event.target === this) {
            $(this).css('display', 'none');
        }
    });

    //Получение
    $(document).on('click', '.casse-link', function(){
        var casseId = $(this).data('id');
        $.ajax({
                    method: "GET",
                    url: '/Casses/' + casseId,
                    success: function(response)
                    {
                        var code = '<span>Deadline: ' + response.deadline + '</span>';
                        $(this).parent().append(code);
                    },
                    error: function(response)
                    {
                        if(response.status == 404) {
                            alert('Дело не найдено');
                        }
                    }
                });
                    return false;
    });

    //Добавление дела
    $('#save-casse').click(function()
    {
        var data = $('#casse-form form').serialize();
        $.ajax({
            method: "POST",
            url: '/Casse/',
            data: data,
            success: function(response)
            {
                $('#casse-form').css('display', 'none');
                var c = {};
                c.id = response;
                var dataArray = $('#casse-form form').serializeArray();
                for(i in dataArray) {
                    c[dataArray[i]['name']] = dataArray[i]['value'];
                   }
                  Casse(c)
            }
        });
            return false;
    });

   });