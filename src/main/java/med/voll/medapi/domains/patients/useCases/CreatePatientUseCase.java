package med.voll.medapi.domains.patients.useCases;

import med.voll.medapi.domains.patients.Patient;
import med.voll.medapi.domains.patients.PatientsRepository;
import med.voll.medapi.domains.patients.dtos.CreatePatientRequest;
import med.voll.medapi.domains.patients.dtos.CreatePatientUseCaseRequest;
import med.voll.medapi.domains.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePatientUseCase {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PatientsRepository patientsRepository;

    public Patient execute(CreatePatientUseCaseRequest data) {
        var user = usersRepository.getReferenceByUsername(data.username());
        var patientData = new CreatePatientRequest(data.email(), data.specialty(), user.getId().toString());
        var patient = new Patient(patientData);
        patientsRepository.save(patient);

        return patient;
    }
}
