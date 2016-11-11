package entity;

import entity.Appointment;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2016-10-26T20:57:05")
@StaticMetamodel(People.class)
public class People_ { 

    public static volatile SingularAttribute<People, String> name;
    public static volatile ListAttribute<People, Appointment> appointmentList;
    public static volatile SingularAttribute<People, String> pid;
    public static volatile SingularAttribute<People, String> email;

}