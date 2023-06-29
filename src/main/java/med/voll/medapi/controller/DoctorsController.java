package med.voll.medapi.controller;

import jakarta.validation.Valid;
import med.voll.medapi.domains.doctor.CreateDoctorRequest;
import med.voll.medapi.domains.doctor.Doctor;
import med.voll.medapi.domains.doctor.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("doctors")
public class DoctorsController {
    @Autowired
    private DoctorsRepository repository;

    @PostMapping
    public void create(@RequestBody @Valid CreateDoctorRequest data) {
        repository.save(new Doctor(data));
    }
}
