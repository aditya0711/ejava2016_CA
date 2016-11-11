/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import entity.People;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author samru
 */
@Stateless
public class PeopleBean {
    
    @PersistenceContext private EntityManager em;
    
    public void addPeople(People people)
    {
        System.out.println("name" + people.getName());
        System.out.println("id" + people.getPid());
        em.persist(people);
    }
    
    public Optional<People> findByEmail(String email){
      TypedQuery<People> query = em.createNamedQuery("People.findByEmail", People.class);

        query.setParameter("email", email);
        try{
            People people=query.getSingleResult();
            return Optional.ofNullable(people);
           }catch(NoResultException e){
            return null;
            }
    }
    
    
}
