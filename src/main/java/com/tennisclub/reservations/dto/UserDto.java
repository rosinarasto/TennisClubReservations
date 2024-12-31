package com.tennisclub.reservations.dto;

import com.tennisclub.reservations.validator.PhoneNumber;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {

    @NotNull
    private String name;

    @PhoneNumber
    @NotNull
    private String phoneNumber;

    @NotNull
    private String password;
}
