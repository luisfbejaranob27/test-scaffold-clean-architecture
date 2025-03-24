package code.luisfbejaranob.scaffold_clean_architecture.infrastructure.driven_adapters.h2;

import code.luisfbejaranob.scaffold_clean_architecture.domain.models.User;
import code.luisfbejaranob.scaffold_clean_architecture.infrastructure.driven_adapters.h2.entities.UserEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserMother
{
    private static final UUID id = UUID.fromString("f4b3b8b9-13e2-4b52-bc5e-6e5c2e5c19b2");

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

    public static UserEntity getUserEntity()
    {
        return UserEntity.builder()
                .id(id)
                .names("John")
                .surnames("Doe")
                .email("john.doe@email.com")
                .createAt(LocalDateTime.parse("2025-03-22T09:16:35.1533816"))
                .updateAt(null)
                .build();
    }

    public static List<User> getUsers()
    {
        return List.of(
                getUser()
        );
    }

    public static List<UserEntity> getUsersEntity()
    {
        return List.of(
                getUserEntity()
        );
    }
}
