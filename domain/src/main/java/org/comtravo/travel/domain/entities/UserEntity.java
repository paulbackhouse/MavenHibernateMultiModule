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

import lombok.Getter;
import lombok.Setter;

@Entity 
@Table(name = "users")
public class UserEntity implements IBaseCreatedModified, Serializable {

    public UserEntity() {}      

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer Id;

    @Column(name = "Username", nullable = false, length = 35)
    @NotEmpty()
    @NotNull()
    @Length(max = 35)
    @Getter @Setter
    private String Username;

    @Column(name = "GivenName", nullable = false, length = 75)
    @NotEmpty()
    @NotNull()
    @Length(max = 75)
    @Getter @Setter
    private String GivenName;

    @Column(name = "LastName", nullable = false, length = 75)
    @NotEmpty()
    @NotNull()
    @Length(max = 75)
    @Getter @Setter
    private String LastName;

    @Column(name = "Created")
    @NotNull()
    @Getter @Setter
    private Timestamp Created;

    @Column(name = "Modified")
    @NotNull()
    @Getter @Setter
    private Timestamp Modified;

}
