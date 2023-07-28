package med.voll.medapi.domains.appointment.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.medapi.domains.doctor.Specialty;

import java.time.LocalDateTime;

public record createAppointmentDTO(
        Long doctorId,
        @NotNull
        Long patientId,
        @NotNull
        @Future
        LocalDateTime date,
        Specialty specialty
) {}