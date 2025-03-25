package code.luisfbejaranob.scaffold_clean_architecture.application.usecases;

import code.luisfbejaranob.scaffold_clean_architecture.application.usecases.dtos.UserRequestDto;
import code.luisfbejaranob.scaffold_clean_architecture.application.usecases.dtos.UserResponseDto;
import code.luisfbejaranob.scaffold_clean_architecture.application.ports.in.UserUseCase;
import code.luisfbejaranob.scaffold_clean_architecture.application.ports.out.UserRepositoryPort;
import code.luisfbejaranob.scaffold_clean_architecture.application.usecases.mappers.UserMapper;
import code.luisfbejaranob.scaffold_clean_architecture.domain.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static code.luisfbejaranob.scaffold_clean_architecture.application.usecases.mappers.UserMapper.*;
import static code.luisfbejaranob.scaffold_clean_architecture.application.usecases.util.ObjectUtil.updateValues;

@Service
public class UserUseCaseImpl implements UserUseCase
{
    private final UserRepositoryPort userRepositoryPort;

    public UserUseCaseImpl(UserRepositoryPort userRepositoryPort)
    {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public UserResponseDto createUser(UserRequestDto dto)
    {
        var user = toDomain(dto);
        user.setCreateAt(LocalDateTime.now());
        return toDto(userRepositoryPort.save(user));
    }

    @Override
    public UserResponseDto getUserById(UUID id)
    {
        return toDto(userRepositoryPort.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }

    @Override
    public List<UserResponseDto> getAllUsers()
    {
        var users = userRepositoryPort.findAll();
        if (users.isEmpty())
        {
            throw new UserNotFoundException();
        }
        return users.stream().map(UserMapper::toDto).toList();
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto dto) throws IllegalAccessException
    {
        var userFound = toDomain(getUserById(dto.getId()));
        return toDto(userRepositoryPort.save(updateValues(userFound, toDomain(dto))));
    }

    @Override
    public void deleteUser(UUID id)
    {
        getUserById(id);
        userRepositoryPort.deleteById(id);
    }
}
