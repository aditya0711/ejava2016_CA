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
import java.util.ArrayList;

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
    public List<Posts> findAll(){
        System.out.println("inside findall notebean");
        TypedQuery<Posts> query = em.createNamedQuery(
				"Posts.findAll", Posts.class);

        List<Posts> posts = query.getResultList();
        System.out.println("bean list " + posts);
	return (posts); 
    }

    public List<Posts> findAll(String category) {
        System.out.println("inside findall by category notebean");
        TypedQuery<Posts> query = em.createNamedQuery(
				"Posts.findByCategory", Posts.class);
        query.setParameter("category", category);

        List<Posts> posts = query.getResultList();
        System.out.println("bean list " + posts);
	return (posts); 
    }

   
}
