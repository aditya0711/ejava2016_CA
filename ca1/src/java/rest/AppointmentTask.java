/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import business.AppointmentBean;
import entity.Appointment;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.Response;

/**
 *
 * @author samru
 */
public class AppointmentTask implements Runnable{

    private String email;
    private AsyncResponse asyncResponse;
    private AppointmentBean appointmentBean;

    public void setAppointmentBean(AppointmentBean appointmentBean) {
        this.appointmentBean = appointmentBean;
    }
    

     public void setAsyncResponse(AsyncResponse asyncResponse) {
        this.asyncResponse = asyncResponse;
    }
   
   
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void getAllApointment(String email){
        this.email=email;
        
    }

    @Override
    public void run() {
        System.out.println("inside run");
            List<Appointment> appointmentList=appointmentBean.getAllApointment(this.email);
            JsonArrayBuilder obj=Json.createArrayBuilder();

                 for(Appointment c: appointmentList) 
                    {
                        System.out.println("inside for "+c.getApptDate());
                        JsonObjectBuilder objBuilder = Json.createObjectBuilder();
                        objBuilder.add("appointmentID",c.getApptId());

                        objBuilder.add("dateTime", c.getApptDate().toString());

                        objBuilder.add("description",c.getDescription());

                        objBuilder.add("personId",c.getPid().toString());

                        obj.add(objBuilder);

                }

                 asyncResponse.resume(Response.ok(obj.build()).build());
}
    
}

