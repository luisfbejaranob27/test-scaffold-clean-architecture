package code.luisfbejaranob.scaffold_clean_architecture.application.usecases;

import code.luisfbejaranob.scaffold_clean_architecture.application.ports.in.UserUseCase;
import code.luisfbejaranob.scaffold_clean_architecture.application.ports.out.UserRepositoryPort;
import code.luisfbejaranob.scaffold_clean_architecture.domain.exceptions.UserNotFoundException;
import code.luisfbejaranob.scaffold_clean_architecture.domain.models.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static code.luisfbejaranob.scaffold_clean_architecture.application.usecases.shared.ObjectUtil.updateValues;

@Service
public class UserUseCaseImpl implements UserUseCase
{
    private final UserRepositoryPort userRepositoryPort;

    public UserUseCaseImpl(UserRepositoryPort userRepositoryPort)
    {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User createUser(User user)
    {
        user.setCreateAt(LocalDateTime.now());
        return userRepositoryPort.save(user);
    }

    @Override
    public User getUserById(UUID id)
    {
        return userRepositoryPort.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public List<User> getAllUsers()
    {
        var users = userRepositoryPort.findAll();
        if (users.isEmpty())
        {
            throw new UserNotFoundException();
        }
        return users;
    }

    @Override
    public User updateUser(User user) throws IllegalAccessException
    {
        var userFound = getUserById(user.getId());
        return userRepositoryPort.save(updateValues(userFound, user));
    }

    @Override
    public void deleteUser(UUID id)
    {
        getUserById(id);
        userRepositoryPort.deleteById(id);
    }
}
