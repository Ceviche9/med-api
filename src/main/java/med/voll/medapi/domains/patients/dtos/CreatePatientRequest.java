package med.voll.medapi.domains.patients.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.medapi.domains.doctor.Specialty;

public record CreatePatientRequest(
        @NotBlank
        @Email
        String email,
        @NotNull
        Specialty specialty,
        @NotBlank
        String doctor_id,
        @NotBlank
        String user_id

) {
        public CreatePatientRequest(CreatePatientUseCaseRequest data, String user_id) {
                this(data.email(), data.specialty(), data.doctor_id(), user_id);
        }

}
