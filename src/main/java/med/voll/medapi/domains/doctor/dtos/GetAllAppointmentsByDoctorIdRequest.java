package med.voll.medapi.domains.doctor.dtos;

import jakarta.validation.constraints.NotNull;

public record GetAllAppointmentsByDoctorIdRequest(
        @NotNull
        String doctor_id
) {
}
