package pl.macieserafin.library_spring.mapper;

import pl.macieserafin.library_spring.dto.UserDto;
import pl.macieserafin.library_spring.model.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }
}
