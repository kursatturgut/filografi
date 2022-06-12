package com.bzhilal.filografi.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Size(max = 15)
    @NotNull(message = "Please enter your firstname")
    @Column(nullable = false,length = 15)
    private  String firstName;

    @Size(max = 15)
    @NotNull(message = "Please enter your lastname")
    @Column(nullable = false,length = 15)
    private  String LastName;

    @Size(min = 4,max = 60)
    @NotNull(message = "Please enter your password")
    @Column(nullable = false,length = 120)
    private  String password;

    @Pattern(regexp = "^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$",
            message = "Please enter valid phone number")
    @Size(min = 14,max = 14)
    @NotNull(message = "Please enter your phoneNumber")
    @Column(nullable = false,length = 14)
    private  String phoneNumber;

    @Email(message = "email enter valid email")
    @Size(min = 5,max = 150)
    @NotNull(message = "Please enter your email")
    @Column(nullable = false,length = 14,unique = true)
    private  String email;

    @Size(max = 255)
    @NotNull(message = "Please enter your address")
    @Column(nullable = false,length = 255)
    private  String  address;

    @Size(max = 15)
    @NotNull(message = "Please enter your zipCode")
    @Column(nullable = false,length = 15)
    private  String zipCode;

    @Column(nullable = false)
    private  Boolean builtIn;









}
