package com.bug_tracker.model.dto;

import com.bug_tracker.model.UserRole;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class UserDto {

    @Positive
    private long id;
    @NotEmpty
    @Size(min = 2, max =20)
    private String firstName;
    @NotEmpty
    @Size(min = 2, max =20)
    private String lastName;
    @Email
    private String email;
    @NotEmpty
    private UserRole role;
    @PastOrPresent
    private LocalDate createdAt;
}
