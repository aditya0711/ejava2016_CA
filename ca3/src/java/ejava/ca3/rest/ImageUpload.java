import ejava.ca3.business.PodBean;
import ejava.ca3.model.Pod;
import ejava.ca3.rest.UploadTask;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

@MultipartConfig
@WebServlet(urlPatterns = {"/upload"})

public class ImageUpload extends HttpServlet {
   
    @EJB private PodBean podBean;
    @Resource(lookup="concurrent/myThreadPool3")
    private ManagedScheduledExecutorService service;
    AsyncResponse asyncResponse;
        
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int podId=Integer.parseInt(req.getParameter("podId"));
        String note=req.getParameter("note");
        Part imagePart=req.getPart("image");
        
        byte[] image = new byte[(int)imagePart.getSize()];
        InputStream is = imagePart.getInputStream();
        
        is.read(image);
        
        Pod pod = new Pod();
        Date date = new Date();
        
        pod.setDeliveryDate(date);
        pod.setPodId(podId);
        pod.setNote(note);
        pod.setImage(image);
       
        podBean.upload(pod);
        
        UploadUsingThread(asyncResponse);
        //UploadToHeadQ(pod.getNote(), pod.getPodId(), pod.getDeliveryDate(), pod.getImage());       
    }
    
    public void UploadUsingThread(@Suspended AsyncResponse asyncResponse){
        UploadTask appTask = new UploadTask();                        
        appTask.setAsyncResponse(asyncResponse);
        appTask.setPodBean(podBean);
        service.schedule(appTask, 10, TimeUnit.SECONDS);
        
        //service.scheduleAtFixedRate(appTask, 5, 20, TimeUnit.SECONDS);
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
}