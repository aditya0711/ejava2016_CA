/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca3.rest;

import ejava.ca3.business.PodBean;
import ejava.ca3.model.Pod;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author Aditya Aggarwal
 */
@WebServlet(urlPatterns = {"/callback"})
public class DataReceiver extends HttpServlet  {
    
@EJB private PodBean podBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        System.out.println("inside get");
        System.out.println(resp.getStatus());

         req.getParameter("podId");
         System.out.println("POD ID   " + req.getParameter("podId"));
         req.getParameter("ackId");       
         System.out.println("Ackno ID   " + req.getParameter("ackId"));

         Pod p= podBean.findById(req.getParameter("podId"));
         p.setAckId(req.getParameter("ackId"));
         podBean.updatePod(p);
    }
    
}
