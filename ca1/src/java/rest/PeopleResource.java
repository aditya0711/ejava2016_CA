/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;


import business.PeopleBean;
import entity.People;
import java.util.Optional;
import java.util.UUID;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

/**
 *
 * @author samru
 */
@RequestScoped
@Path("/people")
public class PeopleResource {
    
        @EJB private PeopleBean peopleBean;
        
	@POST
	@Consumes("application/x-www-form-urlencoded")
        
        public Response post(MultivaluedMap<String, String> formData) {
 
		System.out.println(">>> name: " + formData.getFirst("name"));
                
                String tid = UUID.randomUUID().toString().substring(0, 8);
                System.out.println("id"+tid);
                People people=new People();
                people.setPid(tid);
                people.setName(formData.getFirst("name"));
                people.setEmail(formData.getFirst("email"));
                peopleBean.addPeople(people);
                
		return (Response.ok().build());
	}
        
        @GET
        @Produces(MediaType.APPLICATION_JSON)
       
        public Response findByEmail( @QueryParam("email") String email){
            
             Optional<People> p = peopleBean.findByEmail(email);
            
            if(p != null) {
                
            JsonObjectBuilder apptBuilder = Json.createObjectBuilder();
            apptBuilder.add("pid", p.get().getPid());
            apptBuilder.add("email", p.get().getEmail());
            apptBuilder.add("name", p.get().getName());
            return(Response.ok(apptBuilder.build()).build());
            }else
            {
                return (Response.status(Response.Status.NOT_FOUND).build());
                    
            }
            
        }

    
}
