package org.comtravo.travel.domain.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

public class BookingDto {
  
    public BookingDto() {}

    @NotNull()
    @JsonProperty("id")
    @Getter @Setter
    private Long Id;

    @NotEmpty()
    @NotNull()
    @Getter @Setter
    private Integer UserId;

    @NotEmpty()
    @NotNull()
    @Length(max = 50)
    @Getter @Setter
    private String Departure;

    @NotEmpty()
    @NotNull()
    @Length(max = 50)
    @Getter @Setter
    private String Destination;

    @NotNull()
    @NotEmpty()
    @Getter @Setter
    private Date Depart; 

    @NotNull()
    @NotEmpty()
    @Getter @Setter
    private Short Travellers;

    @Getter @Setter 
    private Date Created;
    
}
