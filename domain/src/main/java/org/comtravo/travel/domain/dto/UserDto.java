package org.comtravo.travel.domain.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

public class UserDto {
  
    public UserDto() {}

    @Getter @Setter 
    @JsonProperty("id")
    private Integer Id;

    @NotEmpty()
    @NotNull()
    @Length(max = 35)
    @JsonProperty("username")
    @Getter @Setter 
    private String Username;

    @NotEmpty()
    @NotNull()
    @Length(max = 75)
    @JsonProperty("givenName")
    @Getter @Setter 
    private String GivenName;

    @NotEmpty()
    @NotNull()
    @Length(max = 75)
    @JsonProperty("lastName")
    @Getter @Setter 
    private String LastName;

    @Getter @Setter 
    private Date Created;

    public String getFullName() {
        return String.format("%1$s %2$s", this.GivenName, this.LastName);
    }

}
