package med.voll.medapi.domains.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentsRepository extends JpaRepository<Appointment, Long> {
    boolean existsByDoctorIdAndDate(Long id, LocalDateTime date);

    List<Appointment> findAllByDoctorId(String doctorId);
}
