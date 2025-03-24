package code.luisfbejaranob.scaffold_clean_architecture.infrastructure.driven_adapters.h2.mappers;

import code.luisfbejaranob.scaffold_clean_architecture.domain.models.User;
import code.luisfbejaranob.scaffold_clean_architecture.infrastructure.driven_adapters.h2.entities.UserEntity;

public class UserMapper
{
    private UserMapper()
    {}

    public static UserEntity toEntity(User user)
    {
        return UserEntity.builder()
                .id(user.getId())
                .names(user.getNames())
                .surnames(user.getSurnames())
                .email(user.getEmail())
                .createAt(user.getCreateAt())
                .updateAt(user.getUpdateAt())
                .build();
    }

    public static User toDomain(UserEntity userEntity)
    {
        return User.builder()
                .id(userEntity.getId())
                .names(userEntity.getNames())
                .surnames(userEntity.getSurnames())
                .email(userEntity.getEmail())
                .createAt(userEntity.getCreateAt())
                .updateAt(userEntity.getUpdateAt())
                .build();
    }
}
