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
        @NotNull
        Specialty specialty,
        @NotBlank
        String doctor_id
) {
        public CreatePatientUseCaseRequest(CreatePatientControllerRequest data, String username) {
                this(username, data.email(), data.specialty(), data.doctor_id());
        }
}
