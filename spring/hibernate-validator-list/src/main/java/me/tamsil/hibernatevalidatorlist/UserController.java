package me.tamsil.hibernatevalidatorlist;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addAll")
    public ResponseEntity<?> addAll(
            @RequestBody
            @NotEmpty(message = "Input user cannot be empty.")
            @MaxSizeConstraint
            List<@Valid UserDto> userDtoList) {
        List<User> resultList = userService.createAll(userDtoList);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
}
