/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca3.rest;

import ejava.ca3.business.PodBean;
import ejava.ca3.model.Pod;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

/**
 *
 * @author Aditya Aggarwal
 */
public class UploadTask implements Runnable {
    private int podId;
    private String note;
    private final String teamId="338ca74e";
    private byte[] image;
    private AsyncResponse asyncResponse;
    private PodBean podBean;

    public PodBean getPodBean() {
        return podBean;
    }

    public void setPodBean(PodBean podBean) {
        this.podBean = podBean;
    }

    public int getPodId() {
        return podId;
    }

    public void setPodId(int podId) {
        this.podId = podId;
    }

    public AsyncResponse getAsyncResponse() {
        return asyncResponse;
    }

    public void setAsyncResponse(AsyncResponse asyncResponse) {
        this.asyncResponse = asyncResponse;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

   
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public void UploadToHeadQ(String note, int podId, Date date, byte[] image) 
			throws ServletException, IOException {
        
        Client client = ClientBuilder.newBuilder()
				.register(MultiPartFeature.class)
				.build();

		MultiPart part = new MultiPart();
                BodyPart bodyPart =new BodyPart(image, MediaType.APPLICATION_OCTET_STREAM_TYPE);
          
                bodyPart.setContentDisposition(FormDataContentDisposition.name("image").fileName("ca3.jpg").build());

		MultiPart formData = new FormDataMultiPart()
				.field("teamId", "338ca74e", MediaType.TEXT_PLAIN_TYPE)
				.field("podId", podId, MediaType.TEXT_PLAIN_TYPE)
                                .field("callback", "http://10.10.25.68:8080/ca3/callback", MediaType.TEXT_PLAIN_TYPE)
                                .field("note", note, MediaType.TEXT_PLAIN_TYPE)
				.bodyPart(bodyPart);
                
		formData.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);

		WebTarget target = client.target("http://10.10.0.48:8080/epod/upload");
                
		Invocation.Builder inv = target.request();

		System.out.println(">>> part: " + formData);

		Response callResp = inv.post(Entity.entity(formData, formData.getMediaType()));

		System.out.println(">> call resp:" + callResp.getStatus() +" "+ callResp.getStatusInfo());
    }
    @Override
    public void run() {
        List<Pod> list = podBean.findAllUnAck();
        for(Pod p:list){
            try {
                UploadToHeadQ(p.getNote(), p.getPodId(), p.getDeliveryDate(), p.getImage());
            } catch (ServletException ex) {
                Logger.getLogger(UploadTask.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(UploadTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
}
