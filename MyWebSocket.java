package com.cetc52.visitors.websocket;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
@Component
public class MyWebSocket {
    private Session session;
//    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();
    private static ArrayList<MyWebSocket> webSocketSet = new ArrayList<MyWebSocket>();
    @OnMessage
    public void onMessage(String message, Session session)
            throws IOException, InterruptedException {

        System.out.println("Received: " + message);

    }

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("---session---"+session.getId());
        webSocketSet.add(this);
        this.session = session;
        System.out.println("Client connected");
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        System.out.println("Connection closed");
    }

    @OnError
    public void onError (Throwable t) {
        System.out.println("没有连接了");
    }

    public static void sendInfo(String message) throws IOException {
        for (MyWebSocket item : webSocketSet) {
            try {
                item.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                continue;
            }
        }
    }
    public String getSessionId(){
        return this.session.getId();
    }
}