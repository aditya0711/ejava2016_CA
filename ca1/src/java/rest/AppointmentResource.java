/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import business.AppointmentBean;
import business.PeopleBean;
import com.oracle.jrockit.jfr.ContentType;
import static com.oracle.jrockit.jfr.ContentType.Timestamp;
import entity.Appointment;
import entity.People;
import java.text.DateFormat;
import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 *
 * @author samru
 */
@RequestScoped
@Path("/appointment")
public class AppointmentResource {
    
   
  @Resource(mappedName = "concurrent/myThreadPool")
    private ManagedExecutorService executors;  

    @EJB AppointmentBean appointmentBean;
    @EJB private  PeopleBean peopleBean;
    List<Appointment> appointments;
    
     @GET
     @Produces(MediaType.APPLICATION_JSON)
     @Path("{email}")
     
    public void getAllAppointments(@PathParam("email") String email,@Suspended AsyncResponse asyncResponse)
    {
        AppointmentTask appTask=new AppointmentTask();
        appTask.setEmail(email);
        appTask.setAsyncResponse(asyncResponse);
        appTask.setAppointmentBean(appointmentBean);
        appTask.getAllApointment(email);
        executors.execute(appTask);
    }
    
    
       @POST
	@Consumes("application/x-www-form-urlencoded")
        
        public Response createAppointment(MultivaluedMap<String, String> formData) {
 
               Appointment appointment = new Appointment();
               Calendar c = Calendar.getInstance();
               c.setTimeInMillis(Long.valueOf(formData.getFirst("date")));
               appointment.setApptDate(c.getTime());
               appointment.setDescription(formData.getFirst("description"));
               String email=formData.getFirst("email");
               Optional<People> people =peopleBean.findByEmail(email);
               appointment.setPid(people.get());
               appointmentBean.bookAppointment(appointment);
               return (Response.ok().build());
	}
        
  
}
