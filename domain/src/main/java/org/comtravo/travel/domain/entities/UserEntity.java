package org.comtravo.travel.domain.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import java.sql.*;

@Entity 
@Table(name = "users")
public class UserEntity implements Serializable{

    public UserEntity() {}      

    // definition

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;

    @Column(name = "Username", nullable = false, length = 35)
    @NotEmpty()
    @NotNull()
    @Length(max = 35)
    private String Username;

    @Column(name = "GivenName", nullable = false, length = 75)
    @NotEmpty()
    @NotNull()
    @Length(max = 75)
    private String GivenName;

    @Column(name = "LastName", nullable = false, length = 75)
    @NotEmpty()
    @NotNull()
    @Length(max = 75)
    private String LastName;

    @Column(name = "Created")
    @NotNull()
    private Timestamp Created;

    @Column(name = "Modified")
    @NotNull()
    private Timestamp Modified; 

    // get & set
    
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getGivenName() {
        return GivenName;
    }

    public void setGivenName(String givenName) {
        GivenName = givenName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public Timestamp getCreated() {
        return Created;
    }

    public void setCreated(Timestamp created) {
        Created = created;
    }

    public Timestamp getModified() {
        return Modified;
    }

    public void setModified(Timestamp modified) {
        Modified = modified;
    }


}
