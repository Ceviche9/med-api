package med.voll.medapi.infra.validations;

import med.voll.medapi.domains.appointment.dtos.createAppointmentDTO;

public interface IScheduleValidation {
    void validate(createAppointmentDTO data);
}
