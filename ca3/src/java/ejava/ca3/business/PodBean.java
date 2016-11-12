/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca3.business;

import ejava.ca3.model.Pod;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author Aditya Aggarwal
 */
@Stateless
public class PodBean {
    @PersistenceContext EntityManager em;
    
    public void insertPod(Pod pod){
        System.out.println("Inside PodBean insert");

        em.persist(pod);
    }
}
