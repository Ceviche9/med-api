package med.voll.medapi.domains.doctor.dtos;

import med.voll.medapi.domains.doctor.Doctor;
import med.voll.medapi.domains.doctor.Specialty;

public record GetAllDoctorsResponse(
        Long id,
        String name,
        String email,
        String crm,
        Specialty specialty
) {
    public GetAllDoctorsResponse(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
