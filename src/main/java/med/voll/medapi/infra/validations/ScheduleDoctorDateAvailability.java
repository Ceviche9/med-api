package med.voll.medapi.infra.validations;

import med.voll.medapi.domains.appointment.AppointmentsRepository;
import med.voll.medapi.domains.appointment.dtos.createAppointmentDTO;
import med.voll.medapi.infra.exceptions.AppError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleDoctorDateAvailability implements IScheduleValidation{

    @Autowired
    private AppointmentsRepository appointmentsRepository;
    public void validate(createAppointmentDTO data) {
        var hasAppointment = appointmentsRepository.existsByDoctorIdAndDate(data.doctorId(), data.date());
        if(hasAppointment) throw new AppError("This doctor already have a appointment at this date and time");
    }
}
