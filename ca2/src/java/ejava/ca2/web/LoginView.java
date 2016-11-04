/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejava.ca2.web;

import ejava.ca2.beans.UserBean;
import ejava.ca2.model.Groups;
import ejava.ca2.model.GroupsPK;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.EJB;
import ejava.ca2.model.Users;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author samru
 */




@ViewScoped
@Named
public class LoginView implements Serializable {
	private static final long serialVersionUID = 1L;
        
        @EJB private UserBean userBean;
        
	private String username;
	private String password;

        
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		HttpServletRequest req = 
				(HttpServletRequest)FacesContext.getCurrentInstance()
						.getExternalContext().getRequest();
		try {
                    System.out.println("inside login"+username+password);
			req.login(username, password);
                        userBean.setuserid(username);
                    } catch (ServletException t) {
			FacesContext.getCurrentInstance()
					.addMessage(null, new FacesMessage("Incorrect login"));
			return null;
		}

		return ("valid/userDisplay?faces-redirect=true");
	}
        
        public String encrypt(String password){
            try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8"));
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String output = bigInt.toString(16);

            return output;

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
        }
            return null;
        }
        
        public String register(){
            Users user = new Users();
            user.setUserid(username);
            user.setPassword(encrypt(password));
            
            GroupsPK groupPK = new GroupsPK();
            groupPK.setGroupid("valid");
            groupPK.setUserid(user.getUserid());
            Groups group = new Groups();
            group.setGroupsPK(groupPK);
            
            try{
                userBean.register(user, group);
                
            }
            catch (Throwable t) {
			FacesContext.getCurrentInstance()
					.addMessage(null, new FacesMessage("User already exists!!"));
                        return null;
		}
            username = "";
            FacesContext.getCurrentInstance()
					.addMessage(null, new FacesMessage("User Registered Successfully. Please Login..."));
            return ("login");
            
        }

}


