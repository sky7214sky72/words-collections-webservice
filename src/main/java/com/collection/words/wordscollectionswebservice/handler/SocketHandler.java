package com.collection.words.wordscollectionswebservice.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class SocketHandler extends TextWebSocketHandler {
    HashMap<String, WebSocketSession> sessionMap = new HashMap<>();
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message){
        //메세지 발송
        String msg = message.getPayload();
        for(String key : sessionMap.keySet()){
            WebSocketSession wss = sessionMap.get(key);
            try{
                wss.sendMessage(new TextMessage(msg));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception{
        //소켓 연결
        super.afterConnectionEstablished(session);
        sessionMap.put(session.getId(),session);
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
        //소켓 종료
        sessionMap.remove(session.getId());
        super.afterConnectionClosed(session, status);
    }
}
