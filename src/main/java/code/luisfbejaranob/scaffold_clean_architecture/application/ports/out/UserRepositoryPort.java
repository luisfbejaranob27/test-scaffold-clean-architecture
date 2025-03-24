package code.luisfbejaranob.scaffold_clean_architecture.application.ports.out;

import code.luisfbejaranob.scaffold_clean_architecture.domain.models.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort
{
    User save(User user);
    Optional<User> findById(UUID id);
    List<User> findAll();
    void deleteById(UUID id);
}
