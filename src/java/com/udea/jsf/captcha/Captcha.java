/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.jsf.captcha;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Juan
 */
public class Captcha implements Serializable{

    /**
     * Creates a new instance of Captcha
     */
    public Captcha() {
    }
    
    public void check(ActionEvent e){
        FacesContext.getCurrentInstance().addMessage(null,
        new FacesMessage(FacesMessage.SEVERITY_INFO,"Su codigo es correcto",null));
    }
    
}
