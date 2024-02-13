package me.tamsil.hibernatevalidatorlist;

import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {

    @NotEmpty(message = "User name cannot be empty")
    private String name;
    private int age;
    private String gender;
    private String phoneNumber;
    private String address;
}
