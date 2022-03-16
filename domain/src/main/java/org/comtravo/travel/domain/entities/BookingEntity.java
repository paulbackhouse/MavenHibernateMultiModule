package org.comtravo.travel.domain.entities;


import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.comtravo.travel.domain.entities.interfaces.IBaseCreatedModified;
import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

import java.sql.*;

@Entity 
@Table(name = "bookings")
public class BookingEntity implements IBaseCreatedModified, Serializable {
    

    public BookingEntity() { }

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Long Id;

    @Column(name = "UserId", nullable = false)
    @NotNull()
    @Getter @Setter
    private Integer UserId;

    @Column(name = "Departure", nullable = false, length = 50)
    @NotEmpty()
    @NotNull()
    @Length(max = 50)
    @Getter @Setter
    private String Departure;

    @Column(name = "Destination", nullable = false, length = 50)
    @NotEmpty()
    @NotNull()
    @Length(max = 50)
    @Getter @Setter
    private String Destination;

    @Column(name = "Depart", nullable = false)
    @NotNull()
    @Getter @Setter
    private Date Depart; 

    @Column(name = "Travellers", nullable = false)
    @NotNull()
    @Getter @Setter
    private Short Travellers;

    @Column(name = "Created")
    @NotNull()
    @Getter @Setter
    private Timestamp Created;

    @Column(name = "Modified")
    @NotNull()
    @Getter @Setter
    private Timestamp Modified;

}
