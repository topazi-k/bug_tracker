package com.bug_tracker.model.dto;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name="user_reg")
public class UserRegistrationDto {

    @Id
    private String email;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String password;
    @Column
    private LocalDate expiryDate;
    @Column
    private String token;

}
