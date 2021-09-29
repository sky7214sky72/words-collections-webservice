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
            window.location.href = '/';
        });
        $('#btn-logout').on('click', function () {
            window.location.href = '/logout';
        });
        $('#btn-google').on('click', function () {
            window.location.href = '/oauth2/authorization/google';
        });
        $('#btn-naver').on('click', function () {
            window.location.href = '/oauth2/authorization/naver';
        });
        $('#move-page').on('click', function () {
            window.location.href = '/?page='+$('#move-number').val();
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
        $('.check-word').on('click',(e)=>{
             if(document.getElementById(e.target.id).value == 'V'){
                 _this.memorize(e.target.id);
             }else{
                 _this.memorizeDelete(e.target.id);
             }
        });
        if(typeof $('#hidden-email').val() != "undefined" || $('#hidden-email').val() != null){
            _this.memorizeRead();
        }
        _this.renderPagination();
    },
    save: function (){
        var data = {
            word : $('#demo-word').val(),
            meaning : $('#demo-meaning').val(),
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
    memorize: function(memorize_value){
        var data = {
            word : memorize_value,
            email : $('#hidden-email').val(),
            name : $('#hidden-name').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/memorize/save',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('암기성공!');
            document.getElementById(memorize_value).value = 'V';
            window.location.reload();
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    memorizeRead: function(){
        var data = $('#hidden-email').val();
        $.ajax({
            type: 'POST',
            url: '/api/memorize/read',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: data
        }).done(function(result){
            for(var i=0;i<result.length;i++){
                memo = result[i].word;
                document.getElementById(memo).value = 'X';
            }
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    memorizeDelete: function(memorize_value){
        var email = $('#hidden-email').val();
        var word = memorize_value;
        $.ajax({
            type: 'DELETE',
            url: '/api/memorize/'+email+'/'+word,
            contentType: 'application/json; charset=utf-8',
        }).done(function(){
            alert('암기취소');
            document.getElementById(memorize_value).value = 'V';
            window.location.reload();
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    renderPagination: function(){
        if($('#totalCount').val() <= 20) return;
        var currentPage = $('#pageNumber').val();
        var totalPage = $('#pageCount').val();
        var pageGroup = Math.ceil(currentPage / 10);
        var last = pageGroup * 10;
        if(last > totalPage) last = totalPage;

        var first = last - (10 - 1) <= 0 ? 1 : last - (10 - 1);
        var next = last + 1;
        var prev = first - 1;
        const fragmentPage = document.createDocumentFragment();
        if(prev > 0){
            var preli = document.createElement('li');
            preli.insertAdjacentHTML("beforeend",`<a href='/?page=${prev}' class='button'>Prev</a>`);
            fragmentPage.appendChild(preli);
        }
        for(var i = first;i<=last;i++){
            const li = document.createElement("li");
            li.insertAdjacentHTML("beforeend",`<a href='/?page=${i}' id='${i}' class='page'>${i}</a>`);
            fragmentPage.appendChild(li);
        }
        if(last<totalPage){
            var endli = document.createElement('li');
            endli.insertAdjacentHTML("beforeend",`<a href='/?page=${next}' class='button'>Next</a>`);
            fragmentPage.appendChild(endli);
        }
        document.getElementById('js-pagination').appendChild(fragmentPage);
        $('#js-pagination a').removeClass("active");
        $(`#js-pagination a#${currentPage}`).addClass("active");
    },
};

main.init();
