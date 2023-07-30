package med.voll.medapi.domains.doctors;

import med.voll.medapi.domains.address.AddressDTO;
import med.voll.medapi.domains.appointment.Appointment;
import med.voll.medapi.domains.doctor.Doctor;
import med.voll.medapi.domains.doctor.DoctorsRepository;
import med.voll.medapi.domains.doctor.Specialty;
import med.voll.medapi.domains.doctor.dtos.CreateDoctorRequest;
import med.voll.medapi.domains.patients.dtos.CreatePatientRequest;
import med.voll.medapi.domains.patients.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

// Para testar a camada de repository
@DataJpaTest
// Para o teste usar o banco de dados real.
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// Para ele ler o application-test.properties
@ActiveProfiles("test")
public class DoctorsRepositoryTest {

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("It Should be able to return null when the only doctor registered is not available.")
    void findAnyByAvailabilityCase1() {
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
        var doctor = createDoctor("Doctor", "doctor@email.com", "111111", Specialty.CARDIOLOGIA);
        var patient = createPatient("Patient", "patient@email.com", Specialty.CARDIOLOGIA, doctor.getId().toString());
        createAppointment(doctor, patient, nextMondayAt10);
        var doctorAvailable = doctorsRepository.findAnyByAvailability(Specialty.CARDIOLOGIA, nextMondayAt10);
        assertThat(doctorAvailable).isNull();
    } // Por padrão o spring faz o rollback no final de cada método.

    @Test
    @DisplayName("It Should be able to return a doctor when his available.")
    void findAnyByAvailabilityCase2() {
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
        var doctor = createDoctor("Doctor", "doctor@email.com", "111111", Specialty.CARDIOLOGIA);
        var doctorAvailable = doctorsRepository.findAnyByAvailability(Specialty.CARDIOLOGIA, nextMondayAt10);
        assertThat(doctorAvailable).isEqualTo(doctor);
    }

    private void createAppointment(Doctor doctor, Patient patient, LocalDateTime data) {
        testEntityManager.persist(new Appointment(null, doctor, patient, data));
    }

    private Doctor createDoctor(String name, String email, String crm, Specialty specialty) {
        var doctor = new Doctor(doctorData(name, email, crm, specialty));
        testEntityManager.persist(doctor);
        return doctor;
    }

    private Patient createPatient(String name, String email, Specialty specialty, String doctor_id) {
        var patient = new Patient(patientData(name, email, specialty, doctor_id));
        testEntityManager.persist(patient);
        return patient;
    }

    private CreateDoctorRequest doctorData(String name, String email, String crm, Specialty specialty) {
        return new CreateDoctorRequest(
                name,
                email,
                "61999999999",
                crm,
                specialty,
                addressData()
        );
    }

    private CreatePatientRequest patientData(String name, String email, Specialty specialty, String doctor_id) {
        return new CreatePatientRequest(
                name,
                email,
                specialty,
                doctor_id
        );
    }

    private AddressDTO addressData() {
        return new AddressDTO(
                "rua xpto",
                "0000000",
                "fortal"
        );
    }
}
