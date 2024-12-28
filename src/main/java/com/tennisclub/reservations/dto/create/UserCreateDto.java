package com.tennisclub.reservations.dto.create;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDto {

    @NonNull
    private String name;

    @NonNull
    private String phoneNumber;

    @NonNull
    private String password;
}
