package ar.edu.utn.udee.controller.backoffice;

import ar.edu.utn.udee.dto.request.RegisterUserDTO;
import ar.edu.utn.udee.dto.response.ErrorResponseDTO;
import ar.edu.utn.udee.models.User;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface BackofficeController {

    @ApiResponses(value = {
            @ApiResponse(
                    code = 200,
                    message = "Successfully updated the stream configuration"),
            @ApiResponse(
                    code = 404, message = "Scope or stream not found", response = ErrorResponseDTO.class),
            @ApiResponse(
                    code = 500, message = "Server error", response = ErrorResponseDTO.class)})
    ResponseEntity<User> registerClient(@RequestBody RegisterUserDTO client);


}

