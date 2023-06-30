package med.voll.medapi.domains.user;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
