package med.voll.medapi.domains.patients.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.medapi.domains.doctor.Specialty;

public record CreatePatientUseCaseRequest(
        @NotBlank
        String username,
        @NotBlank
        @Email
        String email,
        @NotBlank
        Specialty specialty
) {
}
