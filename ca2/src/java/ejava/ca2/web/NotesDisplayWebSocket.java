/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca2.web;

import ejava.ca2.beans.NoteBean;
import ejava.ca2.model.Posts;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Aditya Aggarwal
 */
@Named(value="notesDisplaySock")
@ServerEndpoint("/notesDisplay")
public class NotesDisplayWebSocket {
        private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
        
    @EJB private NoteBean noteBean;
    
    List<Posts> list ;

    @OnMessage
    public String onMessage(String message) {
        return null;
    }

    @OnOpen
    public void onOpen(Session peer) {
        peers.add(peer);
        list = noteBean.findAll("Social");
        setList(list);
        
        System.out.println("Socket open list output: " + list.toString());
    }

    public List<Posts> getList() {
        return list;
    }

    public void setList(List<Posts> list) {
        this.list = list;
    }

    @OnClose
    public void onClose(Session peer) {
        peers.remove(peer);
    }
    
    public static void sendAll(String text) {
        synchronized (peers) {
            for (Session session : peers) {
                if (session.isOpen()) {
                    session.getAsyncRemote().sendText(text);
                }
            }
        }
    }

    
}
