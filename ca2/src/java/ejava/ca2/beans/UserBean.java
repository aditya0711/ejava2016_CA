/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca2.beans;

import ejava.ca2.model.Groups;
import ejava.ca2.model.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author samru
 */
@Stateless
public class UserBean {
    
    @PersistenceContext private EntityManager em;
    Users user = new Users();

    
    public void register(Users user,Groups groups){
        System.out.println("inside register---- bean");
        this.user = user;
        em.persist(user);
        em.persist(groups);

    }
    public String returnUserId(){
        System.out.println("");
        return user.getUserid();
    }
    public void setuserid(String abcd){
        this.user.setUserid(abcd);
    }
}