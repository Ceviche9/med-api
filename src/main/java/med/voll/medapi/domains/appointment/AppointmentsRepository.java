package med.voll.medapi.domains.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentsRepository extends JpaRepository<Appointment, Long> {
}
