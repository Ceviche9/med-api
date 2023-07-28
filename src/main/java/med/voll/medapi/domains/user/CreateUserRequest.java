package med.voll.medapi.domains.user;

import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
        @NotBlank
        String name,
        @NotBlank
        String username,
        @NotBlank
        String password
) {

        public CreateUserRequest setPassword(String password) {
                return new CreateUserRequest(this.name, this.username, password);
        }
}
