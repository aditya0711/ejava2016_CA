/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import ejava.ca2.beans.NoteBean;
import ejava.ca2.model.Posts;
import java.util.Comparator;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Aditya Aggarwal
 */
@RequestScoped
@Path("/findAll")
public class NotesResource {
    
    @EJB private NoteBean noteBean;
    List<Posts> post_list;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray findAll(){
        post_list =  noteBean.findAll();
        sortPostsList(post_list);
        JsonArrayBuilder obj=Json.createArrayBuilder();

                 for(Posts post: post_list) 
                    {
                        System.out.println("inside for " + post.getAuthor());                       
                        JsonObjectBuilder objBuilder = Json.createObjectBuilder();
                        objBuilder.add("postid",post.getPostid());
                        objBuilder.add("title", post.getTitle());
                        objBuilder.add("content",post.getContent());
                        objBuilder.add("author",post.getAuthor());
                        objBuilder.add("timestamp",post.getTimestamp().toString());
                        objBuilder.add("category", post.getCategory());
                        obj.add(objBuilder);
                }
        
        return obj.build();
    }
    
    @GET
    @Path("{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray findAllByCategory(@PathParam("category") String category ){
        
        if(category.equals("all"))
        {
            post_list =  noteBean.findAll();
        }else{
        post_list =  noteBean.findAll(category);
        }
        System.out.println("inside category callling-----");
        sortPostsList(post_list);
        JsonArrayBuilder obj=Json.createArrayBuilder();
        System.out.println("Category: "+ category);
                 for( Posts post: post_list ) 
                    {            
                        JsonObjectBuilder objBuilder = Json.createObjectBuilder();
                        objBuilder.add("postid",post.getPostid());
                        objBuilder.add("title", post.getTitle());
                        objBuilder.add("content",post.getContent());
                        objBuilder.add("author",post.getAuthor());
                        objBuilder.add("timestamp",post.getTimestamp().toString());
                        objBuilder.add("category", post.getCategory());
                        obj.add(objBuilder);
                }
        return obj.build();
    }
    private List<Posts>  sortPostsList(List<Posts> post_list ){
        System.out.println("Inside sorting");
        post_list.sort(new Comparator<Posts>(){
            Boolean flag = false;
            @Override
            public int compare(Posts o1, Posts o2) {
                if(o1.getTimestamp().after(o2.getTimestamp()))
                    flag = true;
                
                if(flag)
                    return 0;
                
                return 1;
            }
    }
        );
        
        
        
        return post_list;
    }
    
}
