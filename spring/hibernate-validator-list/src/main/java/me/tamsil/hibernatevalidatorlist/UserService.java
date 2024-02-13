package me.tamsil.hibernatevalidatorlist;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> createAll(List<UserDto> userDtoList) {
        List<User> userList = userDtoList.stream()
                .map(userDto -> User.builder()
                        .name(userDto.getName())
                        .age(userDto.getAge())
                        .gender(userDto.getGender())
                        .phoneNumber(userDto.getPhoneNumber())
                        .address(userDto.getAddress())
                        .build())
                .collect(Collectors.toList());
        return userRepository.saveAll(userList);
    }

    public User create(UserDto userDto) {
        User user = User.builder()
                .name(userDto.getName())
                .age(userDto.getAge())
                .gender(userDto.getGender())
                .phoneNumber(userDto.getPhoneNumber())
                .address(userDto.getAddress())
                .build();
        return userRepository.save(user);
    }
}
