package org.comtravo.travel.domain.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.Length;

public class UserDto {
  
    public UserDto() {}

    private Integer Id;

    @NotEmpty()
    @NotNull()
    @Length(max = 35)
    private String Username;

    @NotEmpty()
    @NotNull()
    @Length(max = 75)
    private String GivenName;

    @NotEmpty()
    @NotNull()
    @Length(max = 75)
    private String LastName;

    private Date Created;

    public Date getCreated() {
        return Created;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setGivenName(String givenName) {
        GivenName = givenName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setCreated(Date created) {
        Created = created;
    }

    @JsonProperty("id")
    public Integer getId() {
        return Id;
    }

    @JsonProperty("username")
    public String getUsername() {
        return Username;
    }

    @JsonProperty("givenName")
    public String getGivenName() {
        return GivenName;
    }

    public String getFullName() {
        return String.format("%1$s %2$s", this.GivenName, this.LastName);
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return LastName;
    }

}
