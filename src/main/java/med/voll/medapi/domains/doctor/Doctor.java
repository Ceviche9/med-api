package med.voll.medapi.domains.doctor;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.medapi.domains.doctor.dtos.UpdateDoctorRequest;
import med.voll.medapi.domains.address.Address;
import med.voll.medapi.domains.doctor.dtos.CreateDoctorRequest;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone_number;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Specialty specialty;
    @Embedded
    private Address address;
    private Boolean active;

    public Doctor(CreateDoctorRequest data) {
        this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.phone_number = data.phone_number();
        this.crm = data.crm();
        this.specialty = data.specialty();
        this.address = new Address(data.address());
    }

    public void update(UpdateDoctorRequest data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.phone_number() != null) {
            this.phone_number = data.phone_number();
        }

//        if (data.address() != null) {
//            this.address.update(data.address());
//        }
    }

    public void deleteOne() {
        this.active = false;
    }
}
