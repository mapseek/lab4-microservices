package com.khr.userservice.domain;

import com.khr.userservice.dto.ClientDto;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    public ClientDto toDto(){
        return new ClientDto()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email);
    }
}
