package code.luisfbejaranob.scaffold_clean_architecture.infrastructure.driven_adapters.h2.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "users")
public class UserEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String names;
    private String surnames;
    private String email;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
