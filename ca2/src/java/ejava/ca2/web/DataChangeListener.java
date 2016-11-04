/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca2.web;

import ejava.ca2.model.Posts;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.PostPersist;

/**
 *
 * @author Aditya Aggarwal
 */

public class DataChangeListener {
   
    
    ArrayList<Posts> post_list = new ArrayList<Posts>() {};

    @PostPersist
    public void onChange(Posts post) {
        post_list.add(post);
        System.out.println("inside data change listener");
        
        JsonObjectBuilder obj = Json.createObjectBuilder();
        obj.add("postid", post.getPostid());
        obj.add("title", post.getTitle());
        obj.add("content", post.getContent());
        obj.add("category", post.getCategory());
        obj.add("timestamp", post.getTimestamp().toString());
        

        JsonObject o = obj.build();
        System.out.println("json object: " + o );
        // Outcommented because it's broken in current GF/WF versions.
        // event.fire(new MenuChangeEvent(menu));
        NotesDisplayWebSocket.sendAll(o.toString());
    }
}
