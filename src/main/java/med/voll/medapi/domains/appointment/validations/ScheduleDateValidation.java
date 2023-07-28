package med.voll.medapi.domains.appointment.validations;

import med.voll.medapi.domains.appointment.dtos.createAppointmentDTO;
import med.voll.medapi.infra.exceptions.AppError;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class ScheduleDateValidation implements IScheduleValidation{
    public void validate(createAppointmentDTO data) {
        var scheduleDale = data.date();

        var sunday = scheduleDale.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeIsOpen = scheduleDale.getHour() < 7;
        var afterIsClose = scheduleDale.getHour() < 18;

        if (sunday || beforeIsOpen || afterIsClose) throw new AppError("Invalid date");


    }

}