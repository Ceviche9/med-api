package med.voll.medapi.controller;

import jakarta.validation.Valid;
import med.voll.medapi.domains.patients.CreatePatientRequest;
import med.voll.medapi.domains.patients.Patient;
import med.voll.medapi.domains.patients.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("patients")
public class PatientsController {
    @Autowired
    private PatientsRepository repository;
    @PostMapping
    public void create(@RequestBody @Valid CreatePatientRequest data) {
        repository.save(new Patient(data));
    }
}
