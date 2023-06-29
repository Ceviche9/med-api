package med.voll.medapi.domains.doctor.dtos;

import med.voll.medapi.domains.address.Address;
import med.voll.medapi.domains.doctor.Doctor;
import med.voll.medapi.domains.doctor.Specialty;

public record UpdateDoctorResponse(
        Long id,
        String name,
        String email,
        String crm,
        String phone_number,
        Specialty specialty,
        Address address
) {
    public UpdateDoctorResponse(Doctor data) {
        this(data.getId(), data.getName(), data.getEmail(), data.getPhone_number(), data.getCrm(), data.getSpecialty(), data.getAddress());
    }
}
