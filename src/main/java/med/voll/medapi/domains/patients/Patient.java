package med.voll.medapi.domains.patients;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.medapi.domains.doctor.Specialty;


@Table(name = "patients")
@Entity(name ="Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private Specialty specialty;
    private String doctor_id;

    public Patient(CreatePatientRequest data) {
        this.name = data.name();
        this.specialty = data.specialty();
        this.doctor_id = data.doctor_id();
        this.email = data.email();
    }
}
