var main = {
    init : function (){
        var _this = this;
        var ws;
        $("#yourMsg").hide();
        $('#button-send').on('click', function(){
            _this.send();
        });
        $('#button-name').on('click', function(){
            _this.chatName();
        });
        document.addEventListener("keypress",function(e){
            if(e.code === "Enter"){
                _this.send();
            }
        });
    },
    wsOpen : function (){
        ws = new WebSocket("ws://"+location.host+"/chating");
        this.wsEvt();
    },
    wsEvt : function (){
        ws.onopen = function (data){
            //소켓이 열리면 초기화 세팅하기
        }
        ws.onmessage = function (data){
            var msg = data.data;
            if(msg != null && msg.trim() !=''){
                $("#chatting").append("<p>"+msg+"</p>");
            }
        }
    },
    chatName : function (){
        var userName = $("#demo-name").val();
        if(userName == null || userName.trim() == ""){
            alert("이름을 입력해주세요.");
            $("#demo-name").focus();
        }else{
            this.wsOpen();
            $("#yourName").hide();
            $("#yourMsg").show();
        }
    },
    send : function (){
        var uN = $("#demo-name").val();
        var msg = $("#demo-message").val();
        ws.send(uN+":"+msg);
        $("#demo-message").val("");
    }
};

main.init();