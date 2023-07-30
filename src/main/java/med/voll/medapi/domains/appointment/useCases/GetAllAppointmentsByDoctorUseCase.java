package med.voll.medapi.domains.appointment.useCases;

import med.voll.medapi.domains.appointment.Appointment;
import med.voll.medapi.domains.appointment.AppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllAppointmentsByDoctorUseCase {

    @Autowired
    AppointmentsRepository appointmentsRepository;

    public List<Appointment> execute(String doctor_id) {
        var appointments = appointmentsRepository.findAllByDoctorId(doctor_id);

        return appointments;
    }
}
