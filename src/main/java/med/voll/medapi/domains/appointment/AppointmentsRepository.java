package med.voll.medapi.domains.appointment;

import med.voll.medapi.domains.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentsRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndDate(Long id, LocalDateTime date);
}
