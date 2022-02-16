package org.comtravo.travel.domain.entities;


import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.comtravo.travel.domain.entities.interfaces.IBaseCreatedModified;
import org.hibernate.validator.constraints.Length;

import java.sql.*;

@Entity 
@Table(name = "bookings")
public class BookingEntity implements IBaseCreatedModified, Serializable {
    

    public BookingEntity() { }

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "UserId", nullable = false)
    @NotNull()
    private Integer UserId;

    @Column(name = "Departure", nullable = false, length = 50)
    @NotEmpty()
    @NotNull()
    @Length(max = 50)
    private String Departure;

    @Column(name = "Destination", nullable = false, length = 50)
    @NotEmpty()
    @NotNull()
    @Length(max = 50)
    private String Destination;

    @Column(name = "Depart", nullable = false)
    @NotNull()
    private Date Depart; 

    @Column(name = "Travellers", nullable = false)
    @NotNull()
    private Short Travellers;

    @Column(name = "Created")
    @NotNull()
    private Timestamp Created;

    @Column(name = "Modified")
    @NotNull()
    private Timestamp Modified;

    // get & set

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }       

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }
    
    public String getDeparture() {
        return Departure;
    }

    public void setDeparture(String departure) {
        Departure = departure;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public Date getDepart() {
        return Depart;
    }

    public void setDepart(Date depart) {
        Depart = depart;
    }

    public Short getTravellers() {
        return Travellers;
    }

    public void setTravellers(Short travellers) {
        Travellers = travellers;
    }

    @Override
    public Timestamp getCreated() {
        return this.Created;
    }

    @Override
    public void setCreated(Timestamp created) {
        this.Created = created;        
    }

    @Override
    public Timestamp getModified() {
        return this.Modified;
    }

    @Override
    public void setModified(Timestamp modified) {
        this.Modified = modified;
    }
}
