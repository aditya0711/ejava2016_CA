/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca2.beans;

import ejava.ca2.model.Posts;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Aditya Aggarwal
 */

@Stateless
public class NoteBean {
    private Posts post;
    @PersistenceContext private EntityManager em;
    
    public void CreateNote(String title, String content, String author, String Category){
            Posts post = new Posts();
            post.setPostid("12345678");
            post.setAuthor(author);
            post.setCategory(Category);
            post.setContent(content);
            post.setTitle(title);
            
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            
            post.setTimestamp(date);
            
            System.out.println("NoteBean.java syout" + post.getTimestamp() );
            
            em.persist(post);
    }
    public void CreateNote(Posts post){
             em.persist(post);
    }
    public List<Posts> findAllNotes(String author){
        TypedQuery<Posts> query = em.createNamedQuery(
				"Posts.findByAuthor", Posts.class);
		query.setParameter("author", author);

        List<Posts> posts = query.getResultList();
        System.out.println("bean list " + posts);
	return (posts);        
    }
}