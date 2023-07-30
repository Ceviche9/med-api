package med.voll.medapi.domains.patients.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.medapi.domains.doctor.Specialty;

public record CreatePatientControllerRequest(
        @NotBlank
        @Email
        String email,
        @NotNull
        Specialty specialty

) {
}
