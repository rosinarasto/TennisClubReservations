package com.tennisclub.reservations.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "Users")
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class User extends BaseEntity {

    private String name;

    private String phoneNumber;

    private String password;

    private String salt;

    @OneToMany
    private List<Reservation> reservations;
}
