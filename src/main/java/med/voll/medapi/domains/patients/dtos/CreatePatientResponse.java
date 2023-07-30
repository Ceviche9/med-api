package med.voll.medapi.domains.patients.dtos;

import med.voll.medapi.domains.doctor.Specialty;
import med.voll.medapi.domains.patients.Patient;

public record CreatePatientResponse(
        Long id,
        String email,
        Specialty specialty,
        String doctor_id,
        String user_id

) {
    public CreatePatientResponse(Patient patient) {
        this(patient.getId(),patient.getEmail(), patient.getSpecialty(), patient.getDoctor_id(), patient.getUser_id());
    }
}
