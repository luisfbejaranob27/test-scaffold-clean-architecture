package code.luisfbejaranob.scaffold_clean_architecture.infrastructure.entry_points;

import code.luisfbejaranob.scaffold_clean_architecture.application.ports.in.UserUseCase;
import code.luisfbejaranob.scaffold_clean_architecture.application.usecases.dtos.UserRequestDto;
import code.luisfbejaranob.scaffold_clean_architecture.domain.exceptions.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserUseCase useCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createUser() throws Exception
    {
        var payload = UserMother.getPayloadUser();
        var reference = UserMother.getUserResponseDto();

        given(useCase.createUser(any(UserRequestDto.class))).willReturn(reference);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(reference.getId().toString()));

        then(useCase).should(times(1)).createUser(any(UserRequestDto.class));
    }

    @Test
    void getUserById() throws Exception
    {
        var reference = UserMother.getUserResponseDto();
        var id = reference.getId();
        given(useCase.getUserById(id)).willReturn(reference);

        mockMvc.perform(get("/users/%s".formatted(id))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(reference.getId().toString()));

        then(useCase).should(times(1)).getUserById(id);
    }

    @Test
    void getUserByIdThrowUserNotFoundException() throws Exception
    {
        var id = UserMother.getUserRequestDto().getId();
        given(useCase.getUserById(id)).willThrow(new UserNotFoundException(id));

        mockMvc.perform(get("/users/%s".formatted(id))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        then(useCase).should(times(1)).getUserById(id);
    }

    @Test
    void getAllUsers() throws Exception
    {
        var reference = UserMother.getUsersResponseDto();
        given(useCase.getAllUsers()).willReturn(reference);

        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(reference.getFirst().getId().toString()));

        then(useCase).should(times(1)).getAllUsers();
    }

    @Test
    void getAllUsersThrowUserNotFoundException() throws Exception
    {
        given(useCase.getAllUsers()).willThrow(new UserNotFoundException());

        mockMvc.perform(get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        then(useCase).should(times(1)).getAllUsers();

    }

    @Test
    void updateUser() throws Exception
    {
        var payload = UserMother.getUserRequestUpdateDto();
        var reference = UserMother.getUserResponseDto();
        reference.setEmail(payload.getEmail());
        given(useCase.updateUser(any(UserRequestDto.class))).willReturn(reference);

        mockMvc.perform(put("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.email").value(reference.getEmail()));

        then(useCase).should(times(1)).updateUser(any(UserRequestDto.class));
    }

    @Test
    void updateUserThrowUserNotFoundException() throws Exception
    {
        var payload = UserMother.getUserPayloadUpdated();
        given(useCase.updateUser(any(UserRequestDto.class))).willThrow(new UserNotFoundException());

        mockMvc.perform(put("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(payload)))
                .andDo(print())
                .andExpect(status().isNotFound());

        then(useCase).should(times(1)).updateUser(any(UserRequestDto.class));
    }

    @Test
    void deleteUser() throws Exception
    {
        var id = UserMother.getUser().getId();
        willDoNothing().given(useCase).deleteUser(any());

        mockMvc.perform(delete("/users/%s".formatted(id)))
                .andExpect(status().isNoContent());

        then(useCase).should(times(1)).deleteUser(any());
    }

    @Test
    void deleteUserThrowUserNotFoundException() throws Exception
    {
        var id = UserMother.getUser().getId();
        willThrow(new UserNotFoundException()).given(useCase).deleteUser(any());

        mockMvc.perform(delete("/users/%s".formatted(id)))
                .andExpect(status().isNotFound());

        then(useCase).should(times(1)).deleteUser(any());
    }
}