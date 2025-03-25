package code.luisfbejaranob.scaffold_clean_architecture.application.ports.in;

import code.luisfbejaranob.scaffold_clean_architecture.application.usecases.dtos.UserRequestDto;
import code.luisfbejaranob.scaffold_clean_architecture.application.usecases.dtos.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserUseCase
{
    UserResponseDto createUser(UserRequestDto dto);
    UserResponseDto getUserById(UUID id);
    List<UserResponseDto> getAllUsers();
    UserResponseDto updateUser(UserRequestDto dto) throws IllegalAccessException;
    void deleteUser(UUID id);
}
