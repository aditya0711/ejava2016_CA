/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca3.view;

import ejava.ca3.business.DeliveryBean;
import ejava.ca3.business.PodBean;
import ejava.ca3.model.Delivery;
import ejava.ca3.model.Pod;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;

import javax.inject.Named;

/**
 *
 * @author Aditya Aggarwal
 */

@ViewScoped
@Named("deliveryView")
public class DeliveryView implements Serializable{
   private static final long serialVersionUID = 1L;

    private String Name;
    private String Address;
    private String Phone;
   
    @EJB private DeliveryBean deliveryBean;
    @EJB private PodBean podBean;
    
    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }
    public void addDelivery(){
        Date date = new Date();
        Delivery delivery = new Delivery();
       
        delivery.setName(this.Name);
        delivery.setPhone(this.Phone);
        delivery.setAddress(this.Address);
        delivery.setCreateDate(date);
        
        System.out.println("Inside DeliveryView add Delivery" + delivery.getName());
        
        deliveryBean.insertDelivery(delivery);
        
        Pod pod = new Pod();
        pod.setPkgId(delivery);
        pod.setDeliveryDate(date);
        podBean.insertPod(pod);
    }
}
