var main = {
    init : function (){
        var _this = this;
        $('#btn-save').on('click', function(){
            _this.save();
        });
        $('#btn-excel').on('click', function(){
            _this.saveExcel();
        });
        $('#btn-back').on('click', function () {
            window.location.href = '/api/words/list';
        });
        $('#btn-word').on('click', function(){
            if(!$('p[name="word"]').is(':visible')){
                $('p[name="word"]').show();
                $('#btn-word').val("단어 가리기")
            }else{
                $('p[name="word"]').hide();
                $('#btn-word').val("단어 보이기")
            }
        });
        $('#btn-mean').on('click', function(){
            if(!$('p[name="meaning"]').is(':visible')){
                $('p[name="meaning"]').show();
                $('#btn-mean').val("의미 가리기")
            }else{
                $('p[name="meaning"]').hide();
                $('#btn-mean').val("의미 보이기")
            }
        });
        $('#word-category').on('change',function(){
            _this.search();
        });
    },
    save: function (){
        var data = {
            word : $('#demo-word').val(),
            meaning : $('#demo-meaning').val(),
/*            category : $('#demo-category').val()*/
        };

        $.ajax({
            type: 'POST',
            url: '/api/words/posts',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('단어가 등록되었습니다.');
            window.location.reload();
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    saveExcel: function (){
        var data = $('#file');
        data.attr("name", "file");
        var newForm = $('<form></form>');
        newForm.attr("method", "post");
        newForm.attr("enctype","multipart/form-data");
        var clone =data.clone();
        newForm.append(clone);
        var formData = new FormData(newForm[0]);

        $.ajax({
            type: 'POST',
            url: '/api/words/postsExcel',
            enctype: 'multipart/form-data',
            data: formData,
            processData: false,
            contentType: false,
            cache: false
        }).done(function(){
            alert('단어가 등록되었습니다.');
            window.location.reload();
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    search: function (){
        $.ajax({
            type: 'POST',
            url: '/api/words/searchCategory',
            dataType: 'json',
            data: {
                category :$('#word-category').val()
            }
        }).done(function(result){
            console.log(result);
        }).fail(function (error){
            alert(JSON.stringify(error));
            console.log(JSON.stringify(error));
        });
    },
};

main.init();