package com.gladkiei.tasktracker.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Registration request")
public class AuthRequestDto {
    @Schema(description = "Email", example = "user@example.com")
    @Size(min = 5, max = 50, message = "Email must be 5-50 symbols")
    @NotBlank(message = "Email required")
    @Email(message = "Required email format, example user@example.com")
    private String email;

    @Schema(description = "Password", example = "qwerty")
    @Size(min = 5, max = 255, message = "Password must be 5-255 symbols")
    @NotBlank(message = "Password required")
    private String password;

}
