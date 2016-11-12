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
import javax.persistence.TypedQuery;
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
    public void upload(Pod pod){
    TypedQuery<Pod> query = em.createNamedQuery("Pod.findByPodId", Pod.class);     
    query.setParameter("podId", pod.getPodId());
    Pod p = query.getSingleResult();
    
    p.setDeliveryDate(pod.getDeliveryDate());
    p.setImage(pod.getImage());
    p.setNote(pod.getNote());
    p.setPodId(pod.getPodId());
            
    em.merge(p);
    
    }

    public Pod findById(String parameter) {
        Pod po = em.find( Pod.class, Integer.parseInt(parameter));
        return po;    
    }

    public void updatePod(Pod p) {
        em.merge(p);
    }

    public List<Pod> findAllUnAck() {
        TypedQuery<Pod> query = em.createNamedQuery("Pod.findAll", Pod.class);
        List<Pod> list = query.getResultList();
//        List<Pod> list2= query.getResultList();
//        for(Pod pod: list){
//            if(pod.getAckId() != null)
//                list.remove(pod);
//            
//            System.out.println("------> printing pod " + pod.toString());
//
//        }
//        list2 = list;
        System.out.println("------> printing pod " + list.toString());
        return list;  

    }
}
