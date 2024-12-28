package com.tennisclub.reservations.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "\"users\"")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String password;

    private String salt;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;
}
