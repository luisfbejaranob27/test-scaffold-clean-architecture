package code.luisfbejaranob.scaffold_clean_architecture.domain.models;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class User
{
    private UUID id;
    private String names;
    private String surnames;
    private String email;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
