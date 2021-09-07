package com.helpy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Entity(name="users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type",
        discriminatorType = DiscriminatorType.STRING)
@Data
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(nullable = false)
    protected String firstName;
    @Column(nullable = false)
    protected String lastName;
    @Column(nullable = false)
    protected String username;
    @Column(nullable = false)
    protected String password;
    @Email
    protected String email;
    @Column(nullable = false)
    protected String phone;
    @Column(columnDefinition = "DATE")
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate birthdate;
}
