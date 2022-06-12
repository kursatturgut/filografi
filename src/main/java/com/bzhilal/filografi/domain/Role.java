package com.bzhilal.filografi.domain;


import com.bzhilal.filografi.domain.enumeration.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;



    @Enumerated(EnumType.STRING)
    @Column(length =30 )
    private UserRole name;


    @Override
    public String toString() {
        return "Role{" +
                "name=" + name +
                '}';
    }
}
