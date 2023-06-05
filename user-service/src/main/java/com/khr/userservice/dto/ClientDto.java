package com.khr.userservice.dto;

import com.khr.userservice.domain.Client;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ClientDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public Client toDomain() {
        return new Client()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email);
    }
}
