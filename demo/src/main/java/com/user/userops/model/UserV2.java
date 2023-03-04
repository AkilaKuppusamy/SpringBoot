package com.user.userops.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data

public class UserV2 {

    private int id;
    private String name;
    private LocalDate dob;
    private Address address;
    
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder(toBuilder = true)
    @Data

    public static class Address{
    	
    	String AddressLine1;
    	String AddressLine2;
    	String Street;
    	String City;
    	String State;
    }
}
