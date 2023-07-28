package med.voll.medapi.domains.appointment.dtos;

import java.time.LocalDateTime;

public record createdAppointmentDTO(Long id, Long doctorId, Long patientId, LocalDateTime date) {
}
