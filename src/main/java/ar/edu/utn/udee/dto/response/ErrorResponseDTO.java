package ar.edu.utn.udee.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Data
@Builder
public class ErrorResponseDTO implements Serializable {

    private static final long serialVersionUID = -5474957506666799610L;

    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ErrorResponseDTO(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ErrorResponseDTO(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

}
