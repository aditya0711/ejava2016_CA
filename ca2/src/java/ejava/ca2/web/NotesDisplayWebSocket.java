/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca2.web;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Aditya Aggarwal
 */
@ServerEndpoint("/notesDisplay")
public class NotesDisplayWebSocket {

    @OnMessage
    public String onMessage(String message) {
        return null;
    }
    
}
