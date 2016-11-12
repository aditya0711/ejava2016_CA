/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca3.rest;

import ejava.ca3.business.DeliveryBean;
import ejava.ca3.model.Pod;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Aditya Aggarwal
 */
@RequestScoped
@Path(value="/item")

public class PodResource {
    @EJB private DeliveryBean deliveryBean;
    List<Pod> pod_list ;
    String abc;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray findAll() throws Exception{
        pod_list = deliveryBean.findAll();
        System.out.println("print pod list " + pod_list.toString());
        JsonArrayBuilder obj=Json.createArrayBuilder();

                 for(Pod pod: pod_list) 
                    {
                        System.out.println("inside for PodRsc " + abc); 
                        
                        JsonObjectBuilder objBuilder = Json.createObjectBuilder();
                        objBuilder.add("teamId", "338ca74e");
                        objBuilder.add("podId",pod.getPodId());
                        objBuilder.add("address", pod.getPkgId().getAddress());
                        objBuilder.add("name", pod.getPkgId().getName());
                        objBuilder.add("phone", pod.getPkgId().getPhone());                       
                        obj.add(objBuilder);
                }       
        return obj.build();
    }
}
