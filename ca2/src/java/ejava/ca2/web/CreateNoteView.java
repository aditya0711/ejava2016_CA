/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca2.web;

import ejava.ca2.beans.NoteBean;
import ejava.ca2.beans.UserBean;
import ejava.ca2.model.Posts;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Aditya Aggarwal
 */
@ViewScoped
@Named(value="createNoteView")

public class CreateNoteView implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @EJB NoteBean noteBean;
    @EJB UserBean user;
     
    private String title;
    private String content;
    private String author ;
    private int category_index;
    private final String[] category = {"Social", "For Sale", "Jobs", "Tuition"};
 
    private List<Posts> posts_list;

    public int getCategory_index() {
        return category_index;
    }

    public void setCategory_index(int category_index) {
        this.category_index = category_index;
    }

    public List<Posts> getPosts_list() {
        return posts_list;
    }

    public void setPosts_list(List<Posts> posts_list) {
        this.posts_list = posts_list;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String Title) {
        this.title = Title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCategory() {
        return category_index;
    }

    public void setCategory(int Category) {
        this.category_index = Category;
    }

    public void createNewNote(){
        this.author = user.returnUserId();
        System.out.println("inside create new note");
        Date date = new Date();
        String postid = UUID.randomUUID().toString().substring(0, 8);
        Posts post = new Posts(postid, this.title, date, this.content, this.category[category_index] , this.author);
        System.out.println(post.toString() + this.author);       
        noteBean.CreateNote(post.createCopy());     
    }
    public void listPosts(){
        this.author = user.returnUserId();
        posts_list = (List)noteBean.findAllNotes(this.author);
        System.out.println( "list " + posts_list );
    }
    
    
    
}
