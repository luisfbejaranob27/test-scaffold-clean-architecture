package code.luisfbejaranob.scaffold_clean_architecture.application.usecases;

import code.luisfbejaranob.scaffold_clean_architecture.application.ports.out.UserRepositoryPort;
import code.luisfbejaranob.scaffold_clean_architecture.domain.exceptions.UserNotFoundException;
import code.luisfbejaranob.scaffold_clean_architecture.domain.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static code.luisfbejaranob.scaffold_clean_architecture.application.usecases.mappers.UserMapper.toDomain;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserUseCaseImplTest
{
    @Mock
    private UserRepositoryPort repository;

    @InjectMocks
    private UserUseCaseImpl useCase;

    @Test
    void createUser()
    {
        var payload = UserMother.getUserRequestDto();
        var user = toDomain(payload);
        given(repository.save(any(User.class))).willReturn(user);

        var result = useCase.createUser(payload);

        assertEquals(user.getId(), result.getId());

        verify(repository, times(1)).save(any(User.class));
    }

    @Test
    void getUserById()
    {
        var user = UserMother.getUser();
        var id = user.getId();
        given(repository.findById(id)).willReturn(Optional.of(user));

        var result = useCase.getUserById(user.getId());

        assertEquals(user.getId(), result.getId()
        );

        verify(repository, times(1)).findById(id);
    }

    @Test
    void getUserByIdThrowUserNotFoundException()
    {
        var user = UserMother.getUser();
        var id = user.getId();
        given(repository.findById(id)).willReturn(Optional.empty());

        var userId = user.getId();
        assertThrows(UserNotFoundException.class, () -> useCase.getUserById(userId));

        verify(repository, times(1)).findById(id);
    }

    @Test
    void getAllUsers()
    {
        var users = UserMother.getUsers();
        given(repository.findAll()).willReturn(users);

        var result = useCase.getAllUsers();

        assertEquals(users.size(), result.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void getAllUsersThrowUserNotFoundException()
    {
        given(repository.findAll()).willReturn(List.of());

        assertThrows(UserNotFoundException.class, () -> useCase.getAllUsers());

        verify(repository, times(1)).findAll();
    }

    @Test
    void updateUser() throws IllegalAccessException
    {
        var payload = UserMother.getUserRequestUpdateDto();
        var id = payload.getId();
        var user = UserMother.getUser();
        given(repository.findById(id)).willReturn(java.util.Optional.of(user));
        user.setEmail(payload.getEmail());
        given(repository.save(user)).willReturn(user);

        var result = useCase.updateUser(payload);

        assertEquals(user.getEmail(), result.getEmail());

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(user);
    }

    @Test
    void updateUserThrowUserNotFoundException()
    {
        var payload = UserMother.getUserRequestDto();
        var id = payload.getId();
        given(repository.findById(id)).willReturn(java.util.Optional.empty());

        assertThrows(UserNotFoundException.class, () -> useCase.updateUser(payload));

        verify(repository, times(1)).findById(id);
    }

    @Test
    void deleteUser()
    {
        var user = UserMother.getUser();
        given(repository.findById(user.getId())).willReturn(java.util.Optional.of(user));

        useCase.deleteUser(user.getId());

        verify(repository, times(1)).findById(user.getId());
        verify(repository, times(1)).deleteById(user.getId());
    }

    @Test
    void deleteUserThrowUserNotFoundException()
    {
        var user = UserMother.getUser();
        var userId = user.getId();
        given(repository.findById(user.getId())).willReturn(java.util.Optional.empty());

        assertThrows(UserNotFoundException.class, () -> useCase.deleteUser(userId));

        verify(repository, times(1)).findById(user.getId());
    }
}