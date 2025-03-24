package code.luisfbejaranob.scaffold_clean_architecture.infrastructure.shared.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorDto
{
    private int status;
    private String message;
    private List<String> errors;

    public ApiErrorDto(int status, String message)
    {
        this.status = status;
        this.message = message;
    }

    public ApiErrorDto(int status, String message, List<String> error)
    {
        this.status = status;
        this.message = message;
        this.errors = error;
    }
}