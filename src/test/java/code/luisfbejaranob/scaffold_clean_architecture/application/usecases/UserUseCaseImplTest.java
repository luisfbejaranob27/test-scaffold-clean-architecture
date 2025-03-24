package code.luisfbejaranob.scaffold_clean_architecture.application.usecases;

import code.luisfbejaranob.scaffold_clean_architecture.application.ports.out.UserRepositoryPort;
import code.luisfbejaranob.scaffold_clean_architecture.domain.exceptions.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        var payload = UserMother.getPayloadUser();
        var user = UserMother.getUser();
        given(repository.save(payload)).willReturn(user);

        var result = useCase.createUser(payload);

        assertEquals(user, result);

        verify(repository, times(1)).save(payload);
    }

    @Test
    void getUserById()
    {
        var user = UserMother.getUser();
        given(repository.findById(user.getId())).willReturn(Optional.of(user));

        var result = useCase.getUserById(user.getId());

        assertEquals(user, result);

        verify(repository, times(1)).findById(user.getId());
    }

    @Test
    void getUserByIdThrowUserNotFoundException()
    {
        var user = UserMother.getUser();
        given(repository.findById(user.getId())).willReturn(Optional.empty());

        var userId = user.getId();
        assertThrows(UserNotFoundException.class, () -> useCase.getUserById(userId));

        verify(repository, times(1)).findById(user.getId());
    }

    @Test
    void getAllUsers()
    {
        var users = UserMother.getUsers();
        given(repository.findAll()).willReturn(users);

        var result = useCase.getAllUsers();

        assertEquals(users, result);

        verify(repository, times(1)).findAll();
    }

    @Test
    void getAllUsersThrowUserNotFoundException()
    {
        given(repository.findAll()).willReturn(java.util.List.of());

        assertThrows(UserNotFoundException.class, () -> useCase.getAllUsers());

        verify(repository, times(1)).findAll();
    }

    @Test
    void updateUser() throws IllegalAccessException
    {
        var user = UserMother.getUser();
        given(repository.findById(user.getId())).willReturn(java.util.Optional.of(user));
        user.setEmail("john.doe@gmail.com");
        given(repository.save(user)).willReturn(user);

        var result = useCase.updateUser(user);

        assertEquals(user, result);

        verify(repository, times(1)).findById(user.getId());
        verify(repository, times(1)).save(user);
    }

    @Test
    void updateUserThrowUserNotFoundException()
    {
        var user = UserMother.getUser();
        given(repository.findById(user.getId())).willReturn(java.util.Optional.empty());

        assertThrows(UserNotFoundException.class, () -> useCase.updateUser(user));

        verify(repository, times(1)).findById(user.getId());
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
        given(repository.findById(user.getId())).willReturn(java.util.Optional.empty());

        var userId = user.getId();
        assertThrows(UserNotFoundException.class, () -> useCase.deleteUser(userId));

        verify(repository, times(1)).findById(user.getId());
    }
}