package med.voll.medapi.domains.patients;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.medapi.domains.doctor.Specialty;

public record CreatePatientRequest(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotNull
        Specialty specialty,
        @NotBlank
        String doctor_id

) {
}
