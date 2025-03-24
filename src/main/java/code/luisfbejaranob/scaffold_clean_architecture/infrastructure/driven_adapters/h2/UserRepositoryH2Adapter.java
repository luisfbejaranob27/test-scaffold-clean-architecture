package code.luisfbejaranob.scaffold_clean_architecture.infrastructure.driven_adapters.h2;

import code.luisfbejaranob.scaffold_clean_architecture.application.ports.out.UserRepositoryPort;
import code.luisfbejaranob.scaffold_clean_architecture.domain.models.User;
import code.luisfbejaranob.scaffold_clean_architecture.infrastructure.driven_adapters.h2.mappers.UserMapper;
import code.luisfbejaranob.scaffold_clean_architecture.infrastructure.driven_adapters.h2.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static code.luisfbejaranob.scaffold_clean_architecture.infrastructure.driven_adapters.h2.mappers.UserMapper.toDomain;
import static code.luisfbejaranob.scaffold_clean_architecture.infrastructure.driven_adapters.h2.mappers.UserMapper.toEntity;

@Component
public class UserRepositoryH2Adapter implements UserRepositoryPort
{
    private final UserRepository userRepository;

    public UserRepositoryH2Adapter(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user)
    {
        return toDomain(userRepository.save(toEntity(user)));
    }

    @Override
    public Optional<User> findById(UUID id)
    {
        return userRepository.findById(id).map(UserMapper::toDomain);
    }

    @Override
    public List<User> findAll()
    {
        return userRepository.findAll().stream().map(UserMapper::toDomain).toList();
    }

    @Override
    public void deleteById(UUID id)
    {
        userRepository.deleteById(id);
    }
}
