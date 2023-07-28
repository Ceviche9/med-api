package med.voll.medapi.domains.appointment.validations;

import med.voll.medapi.domains.appointment.dtos.createAppointmentDTO;
import med.voll.medapi.infra.exceptions.AppError;

import java.time.Duration;
import java.time.LocalDateTime;

public class ScheduleHourValidation implements IScheduleValidation {
    public void validate(createAppointmentDTO data) {
        var scheduleDale = data.date();
        var currentHour = LocalDateTime.now();
        var differenceInMinutes = Duration.between(currentHour, scheduleDale).toMinutes();

        if (differenceInMinutes < 30) throw  new AppError("The appointment must be scheduled at least 30 minutes in advance.");
    }
}
