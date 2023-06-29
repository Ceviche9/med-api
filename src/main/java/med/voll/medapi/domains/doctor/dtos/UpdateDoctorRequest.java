package med.voll.medapi.domains.doctor.dtos;

import jakarta.validation.constraints.NotNull;
import med.voll.medapi.domains.address.Address;

public record UpdateDoctorRequest(
        @NotNull
        Long id,
        String name,
        String phone_number,
        Address address

) {
}
