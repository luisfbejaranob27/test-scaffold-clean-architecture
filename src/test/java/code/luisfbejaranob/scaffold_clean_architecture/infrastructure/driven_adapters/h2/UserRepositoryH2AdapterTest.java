package code.luisfbejaranob.scaffold_clean_architecture.infrastructure.driven_adapters.h2;

import code.luisfbejaranob.scaffold_clean_architecture.infrastructure.driven_adapters.h2.entities.UserEntity;
import code.luisfbejaranob.scaffold_clean_architecture.infrastructure.driven_adapters.h2.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static code.luisfbejaranob.scaffold_clean_architecture.infrastructure.driven_adapters.h2.mappers.UserMapper.toEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserRepositoryH2AdapterTest
{
    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserRepositoryH2Adapter adapter;

    @Test
    void save()
    {
        var user = UserMother.getUser();
        var userEntity = UserMother.getUserEntity();
        given(repository.save(any(UserEntity.class))).willReturn(userEntity);

        var result = adapter.save(user);

        assertEquals(user, result);

        verify(repository, times(1)).save(userEntity);
    }

    @Test
    void save2()
    {
        var user = UserMother.getUser();
        var userEntity = UserMother.getUserEntity();
        given(repository.save(toEntity(user))).willReturn(userEntity);

        var result = adapter.save(user);

        assertEquals(user, result);

        verify(repository, times(1)).save(userEntity);
    }

    @Test
    void findById()
    {
        var user = UserMother.getUser();
        var userEntity = UserMother.getUserEntity();
        given(repository.findById(user.getId())).willReturn(java.util.Optional.of(userEntity));

        var result = adapter.findById(user.getId());

        assertTrue(result.isPresent());
        assertEquals(user, result.get());

        verify(repository, times(1)).findById(user.getId());
    }

    @Test
    void findAll()
    {
        var users = UserMother.getUsers();
        var usersEntity = UserMother.getUsersEntity();
        given(repository.findAll()).willReturn(usersEntity);

        var result = adapter.findAll();

        assertEquals(users.size(), result.size());

        verify(repository, times(1)).findAll();
    }

    @Test
    void deleteById()
    {
        var user = UserMother.getUser();
        adapter.deleteById(user.getId());

        verify(repository, times(1)).deleteById(user.getId());
    }
}