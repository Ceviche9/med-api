package med.voll.medapi.domains.patients;

import med.voll.medapi.domains.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientsRepository extends JpaRepository<Patient, Long> {
    @Query("""
            select p from Patient p
            where p.user_id = :user_id
            """)
    Patient getReferenceByUserId(String user_id);
}
