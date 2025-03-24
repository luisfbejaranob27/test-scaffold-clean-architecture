package code.luisfbejaranob.scaffold_clean_architecture.infrastructure.driven_adapters.h2.repositories;

import code.luisfbejaranob.scaffold_clean_architecture.infrastructure.driven_adapters.h2.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID>
{}