package code.luisfbejaranob.scaffold_clean_architecture.infrastructure.entry_points;

import code.luisfbejaranob.scaffold_clean_architecture.application.ports.in.UserUseCase;
import code.luisfbejaranob.scaffold_clean_architecture.application.usecases.dtos.UserRequestDto;
import code.luisfbejaranob.scaffold_clean_architecture.application.usecases.dtos.UserResponseDto;
import code.luisfbejaranob.scaffold_clean_architecture.domain.exceptions.UserNotFoundException;
import code.luisfbejaranob.scaffold_clean_architecture.infrastructure.shared.dtos.ApiErrorDto;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController
{
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase)
    {
        this.userUseCase = userUseCase;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto dto)
    {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userUseCase.createUser(dto));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable UUID id)
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userUseCase.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers()
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userUseCase.getAllUsers());
    }

    @PutMapping
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody @Valid UserRequestDto dto) throws IllegalAccessException
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userUseCase.updateUser(dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id)
    {
        userUseCase.deleteUser(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorDto> handleUserNotFoundException(UserNotFoundException e)
    {
        var apiError = new ApiErrorDto(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage()
        );
        log.error("TypeMismatchException: ", e);

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(apiError);
    }
}
