package med.voll.medapi.domains.user;

import jakarta.validation.constraints.NotBlank;

public record AuthenticateRequest(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
