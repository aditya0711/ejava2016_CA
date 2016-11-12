/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca3.business;

import ejava.ca3.model.Delivery;
import ejava.ca3.model.Pod;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author samru
 */
@Stateless
public class DeliveryBean {
    
    @PersistenceContext EntityManager em;
    @EJB private PodBean podBean;
    Delivery delivery = new Delivery();
    
    public void insertDelivery(Delivery delivery){
        System.out.println("Inside DeliveryBean insert");
        this.delivery = delivery;
        em.persist(delivery);
    }
    public List<Pod> findAll(){
        TypedQuery<Pod> query = em.createNamedQuery(
				"Delivery.getList", Pod.class);
        
        System.out.println("DeliveryBean findAll" + query.getResultList().get(0).getDeliveryDate());
        
        return query.getResultList();
    }
    
}
