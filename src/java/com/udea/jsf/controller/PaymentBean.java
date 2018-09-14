/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udea.jsf.controller;

import com.udea.logica.PaymentFacadeLocal;
import com.udea.modelo.Payment;
import java.io.Serializable;
import java.util.Date;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author Juan
 */
public class PaymentBean implements Serializable{
    @EJB
    private PaymentFacadeLocal paymentFacade;

    private UIComponent mybutton;

    public UIComponent getMybutton() {
        return mybutton;
    }

    public void setMybutton(UIComponent mybutton) {
        this.mybutton = mybutton;
    }
    
    
    /**
     * Creates a new instance of PaymentBean
     */
    public PaymentBean() {
    }
    
    //defino los atributos contra la vista
    private int id;
    private double amount;
    private long card;
    private Date date= new Date();
    private int cvvNo;
    private String contrasenia;
    private String sSubCadena;
    private String mensajecard;
    private String m;
    private String txtContraseniaRepita;
    private boolean disable= true;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getCard() {
        return card;
    }

    public void setCard(long card) {
        this.card = card;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCvvNo() {
        return cvvNo;
    }

    public void setCvvNo(int cvvNo) {
        this.cvvNo = cvvNo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getsSubCadena() {
        return sSubCadena;
    }

    public void setsSubCadena(String sSubCadena) {
        this.sSubCadena = sSubCadena;
    }

    public String getMensajecard() {
        return mensajecard;
    }

    public void setMensajecard(String mensajecard) {
        this.mensajecard = mensajecard;
    }

    public String getM() {
        return m;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getTxtContraseniaRepita() {
        return txtContraseniaRepita;
    }

    public void setTxtContraseniaRepita(String txtContraseniaRepita) {
        this.txtContraseniaRepita = txtContraseniaRepita;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }
    
    
    //accion para insertar un registro
    public String guardar(){
        Payment p = new Payment();
        p.setId(id);
        p.setAmount(amount);
        p.setCard(card);
        p.setCvv(cvvNo);
        p.setDate(date);
        this.paymentFacade.create(p);
        m=this.getMensajecard();
        return "PaymentCreate";
    }
    //permite validar el tipo de tarjeta de credito
    //validad el rango de los primeros 4 digitos segun el tipo de tarjeta 
    //visa o master card si esta en el rango activa el boton
    public String validar(){
        String sCadena;
        sCadena=String.valueOf(card);
        sSubCadena= sCadena.substring(0,4);
        int val= Integer.parseInt(sSubCadena);
        if(val>=0000 && val<=5555){
            FacesMessage message = new FacesMessage("TARJETA VISA");
            FacesContext context= FacesContext.getCurrentInstance();
            context.addMessage(mybutton.getClientId(context), message);
            mensajecard="Tarjeta Visa";
            disable = false;//activo el boton
            this.setMensajecard(mensajecard);
        }else if(val>=8000 && val<=9999){
            FacesMessage message = new FacesMessage("TARJETA MasterCard");
            FacesContext context= FacesContext.getCurrentInstance();
            context.addMessage(mybutton.getClientId(context), message);
            mensajecard="Tarjeta MasterCard";
            disable = false;//activo el boton
            this.setMensajecard(mensajecard);
        }else {
            FacesMessage message = new FacesMessage("TARJETA Invalida");
            FacesContext context= FacesContext.getCurrentInstance();
            context.addMessage(mybutton.getClientId(context), message);
        }
        return null;
    
    }
    //para llamar la internacionalisacion
    private Locale locale= FacesContext.getCurrentInstance().getViewRoot().getLocale();
    public Locale getLocale(){
        return locale;
    }
    //setear el diioma desde la vista 
    public String getLanguage(){
        return locale.getLanguage();
    }
    //cambiar idioma
    public void changeLanguage(String language){
        locale= new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
    }
}
