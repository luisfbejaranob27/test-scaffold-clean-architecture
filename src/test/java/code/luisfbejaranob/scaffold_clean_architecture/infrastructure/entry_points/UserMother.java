package code.luisfbejaranob.scaffold_clean_architecture.infrastructure.entry_points;

import code.luisfbejaranob.scaffold_clean_architecture.application.usecases.dtos.UserRequestDto;
import code.luisfbejaranob.scaffold_clean_architecture.application.usecases.dtos.UserResponseDto;
import code.luisfbejaranob.scaffold_clean_architecture.domain.models.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserMother
{
    private static final UUID id = UUID.fromString("f4b3b8b9-13e2-4b52-bc5e-6e5c2e5c19b2");

    public static User getPayloadUser()
    {
        return User.builder()
                .names("John")
                .surnames("Doe")
                .email("john.doe@email.com")
                .build();
    }

    public static User getUser()
    {
        return User.builder()
                .id(id)
                .names("John")
                .surnames("Doe")
                .email("john.doe@email.com")
                .createAt(LocalDateTime.parse("2025-03-22T09:16:35.1533816"))
                .updateAt(null)
                .build();
    }

    public static User getUserPayloadUpdated()
    {
        return User.builder()
                .id(id)
                .names("John")
                .surnames("Doe")
                .email("john.doe@gmail.com")
                .createAt(LocalDateTime.parse("2025-03-22T09:16:35.1533816"))
                .createAt(LocalDateTime.now())
                .build();
    }

    public static User getUserUpdated()
    {
        return User.builder()
                .id(id)
                .names("John")
                .surnames("Doe")
                .email("john.doe@gmail.com")
                .createAt(LocalDateTime.parse("2025-03-22T09:16:35.1533816"))
                .createAt(LocalDateTime.now())
                .build();
    }

    public static List<User> getUsers()
    {
        return List.of(
                getUser()
        );
    }

    public static UserRequestDto getUserRequestDto()
    {
        return UserRequestDto.builder()
                .id(id)
                .names("John")
                .surnames("Doe")
                .email("john.doe@gmail.com")
                .createAt(null)
                .updateAt(null)
                .build();
    }

    public static UserRequestDto getUserRequestUpdateDto()
    {
        return UserRequestDto.builder()
                .id(id)
                .names("John")
                .surnames("Doe")
                .email("john.doe@outlook.com")
                .createAt(null)
                .updateAt(null)
                .build();
    }

    public static UserResponseDto getUserResponseDto()
    {
        return UserResponseDto.builder()
                .id(id)
                .names("John")
                .surnames("Doe")
                .email("john.doe@gmail.com")
                .createAt(LocalDateTime.parse("2025-03-22T09:16:35.1533816"))
                .updateAt(null)
                .build();
    }

    public static List<UserResponseDto> getUsersResponseDto()
    {
        return List.of(
                getUserResponseDto()
        );
    }
}
