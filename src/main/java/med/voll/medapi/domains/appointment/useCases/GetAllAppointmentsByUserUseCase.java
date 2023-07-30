package med.voll.medapi.domains.appointment.useCases;

import med.voll.medapi.domains.appointment.Appointment;
import med.voll.medapi.domains.appointment.AppointmentsRepository;
import med.voll.medapi.domains.patients.PatientsRepository;
import med.voll.medapi.domains.user.UsersRepository;
import med.voll.medapi.infra.exceptions.AppError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllAppointmentsByUserUseCase {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    public List<Appointment> execute(Long patientId) {
        var patient = patientsRepository.getReferenceById(patientId);

        if(patient == null) throw new AppError("Patient not found");

        var appointments = appointmentsRepository.findAllByPatientId(patient.getId().toString());

        return appointments;
    }
}
