package med.voll.medapi.domains.appointment.useCases;

import med.voll.medapi.domains.appointment.Appointment;
import med.voll.medapi.domains.appointment.AppointmentsRepository;
import med.voll.medapi.domains.appointment.dtos.createAppointmentDTO;
import med.voll.medapi.domains.user.UsersRepository;
import med.voll.medapi.infra.validations.IScheduleValidation;
import med.voll.medapi.domains.doctor.Doctor;
import med.voll.medapi.domains.doctor.DoctorsRepository;
import med.voll.medapi.domains.patients.PatientsRepository;
import med.voll.medapi.infra.exceptions.AppError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ScheduleAppointmentsUseCase {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AppointmentsRepository appointmentsRepository;

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    @Autowired
    // O spring vai procurar todas as classes que usam essa interface e vão injetar elas em uma lista.
    private List<IScheduleValidation> validators;

    public Appointment execute(createAppointmentDTO data, String username) {
        var user = usersRepository.getReferenceByUsername(username);

        System.out.println("Aqui" + user.getId());
        var patient = patientsRepository.getReferenceByUserId(user.getId().toString());
        System.out.println("patient" + patient);

        if(patient == null) throw new AppError("Patient not found");

        if(data.doctorId() != null && !doctorsRepository.existsById(data.doctorId()))
            throw new AppError("Doctor not found");

        var doctor = choseDoctor(data);
        System.out.println("Doctor " + doctor);
        if(doctor == null) throw new AppError("There is no doctors available at this date");

        validators.forEach(validator -> validator.validate(data));

        var appointment = new Appointment(null, doctor, patient, data.date());
        var response = appointmentsRepository.save(appointment);

        return response;
    }

    private Doctor choseDoctor(createAppointmentDTO data) {
        if(data.doctorId() != null) {
            return doctorsRepository.getReferenceById(data.doctorId());
        }

        if (data.specialty() == null) {
            throw new AppError("The specialty is mandatory when the doctor is not chosen");
        }

        return doctorsRepository.findAnyByAvailability(data.specialty(), data.date());
    }
}
