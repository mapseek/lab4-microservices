package com.khr.paymentservice.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ClientDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
