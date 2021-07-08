var main = {
    init : function (){
        var _this = this;
        //_this.loadhtml();
    },
    loadhtml : function (){
        $('#videocontent').load("http://localhost:82/webrtc.html");
    }
};

main.init();