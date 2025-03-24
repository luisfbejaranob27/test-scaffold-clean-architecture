package code.luisfbejaranob.scaffold_clean_architecture.domain.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException()
    {
        super("Users not found");
    }

    public UserNotFoundException(UUID id)
    {
        super("User with id %s not found".formatted(id));
    }
}
