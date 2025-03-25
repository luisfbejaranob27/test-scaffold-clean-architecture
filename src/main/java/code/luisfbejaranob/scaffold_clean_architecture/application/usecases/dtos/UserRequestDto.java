package code.luisfbejaranob.scaffold_clean_architecture.application.usecases.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserRequestDto
{
    private UUID id;

    @NotBlank
    @Size(min = 1, max = 50)
    private String names;

    @NotBlank
    @Size(min = 1, max = 50)
    private String surnames;

    @NotBlank
    @Size(min = 1, max = 150)
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "The email must have a valid format")
    private String email;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
