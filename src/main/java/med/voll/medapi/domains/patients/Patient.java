package med.voll.medapi.domains.patients;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.medapi.domains.doctor.Specialty;
import med.voll.medapi.domains.patients.dtos.CreatePatientRequest;


@Table(name = "patients")
@Entity(name ="Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private Specialty specialty;
    private String user_id;

    public Patient(CreatePatientRequest data) {
        this.specialty = data.specialty();
        this.email = data.email();
        this.user_id = data.user_id();
    }
}
