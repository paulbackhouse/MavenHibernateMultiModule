package org.comtravo.travel.domain.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.comtravo.travel.domain.entities.interfaces.IBaseCreatedModified;
import org.hibernate.validator.constraints.Length;

@Entity 
@Table(name = "users")
public class UserEntity implements IBaseCreatedModified, Serializable {

    public UserEntity() {}      

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
