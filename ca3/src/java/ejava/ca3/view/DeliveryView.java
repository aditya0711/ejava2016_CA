/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca3.view;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author Aditya Aggarwal
 */
@RequestScoped
@Named(value="deliveryView")
public class DeliveryView {
    private String Name;
    private String Address;
    private String Phone;

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
        
    }
            
}
