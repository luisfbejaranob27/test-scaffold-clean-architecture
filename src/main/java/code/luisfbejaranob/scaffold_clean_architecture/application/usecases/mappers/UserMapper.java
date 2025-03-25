package code.luisfbejaranob.scaffold_clean_architecture.application.usecases.mappers;

import code.luisfbejaranob.scaffold_clean_architecture.application.usecases.dtos.UserRequestDto;
import code.luisfbejaranob.scaffold_clean_architecture.application.usecases.dtos.UserResponseDto;
import code.luisfbejaranob.scaffold_clean_architecture.domain.models.User;

public class UserMapper
{
    private UserMapper()
    {}

    public static UserResponseDto toDto(User user)
    {
        return UserResponseDto.builder()
                .id(user.getId())
                .names(user.getNames())
                .surnames(user.getSurnames())
                .email(user.getEmail())
                .createAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .build();
    }

    public static User toDomain(UserRequestDto dto)
    {
        return User.builder()
                .names(dto.getNames())
                .surnames(dto.getSurnames())
                .email(dto.getEmail())
                .createAt(dto.getCreateAt())
                .updateAt(dto.getUpdateAt())
                .build();
    }

    public static User toDomain(UserResponseDto dto)
    {
        return User.builder()
                .id(dto.getId())
                .names(dto.getNames())
                .surnames(dto.getSurnames())
                .email(dto.getEmail())
                .createAt(dto.getCreateAt())
                .updateAt(dto.getUpdateAt())
                .build();
    }
}
