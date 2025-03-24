package code.luisfbejaranob.scaffold_clean_architecture.application.ports.in;

import code.luisfbejaranob.scaffold_clean_architecture.domain.models.User;

import java.util.List;
import java.util.UUID;

public interface UserUseCase
{
    User createUser(User user);
    User getUserById(UUID id);
    List<User> getAllUsers();
    User updateUser(User user) throws IllegalAccessException;
    void deleteUser(UUID id);
}
