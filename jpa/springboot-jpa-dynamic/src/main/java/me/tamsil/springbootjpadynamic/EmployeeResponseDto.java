package me.tamsil.springbootjpadynamic;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeResponseDto {
    private Long id;
    private String name;
    private String gender;
    private String phoneNumber;
    private String location;

    @Builder
    public EmployeeResponseDto(Long id, String name, String gender, String phoneNumber, String location) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }
}
