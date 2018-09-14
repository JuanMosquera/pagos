package com.udea.modelo;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-09-13T21:09:21")
@StaticMetamodel(Payment.class)
public class Payment_ { 

    public static volatile SingularAttribute<Payment, Date> date;
    public static volatile SingularAttribute<Payment, Double> amount;
    public static volatile SingularAttribute<Payment, Integer> cvv;
    public static volatile SingularAttribute<Payment, Integer> id;
    public static volatile SingularAttribute<Payment, Long> card;

}