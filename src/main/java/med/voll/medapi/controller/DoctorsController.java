package med.voll.medapi.controller;

import jakarta.validation.Valid;
import med.voll.medapi.domains.doctor.dtos.GetAllDoctorsResponse;
import med.voll.medapi.domains.doctor.dtos.UpdateDoctorRequest;
import med.voll.medapi.domains.doctor.dtos.CreateDoctorRequest;
import med.voll.medapi.domains.doctor.Doctor;
import med.voll.medapi.domains.doctor.DoctorsRepository;
import med.voll.medapi.domains.doctor.dtos.UpdateDoctorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("doctors")
public class DoctorsController {
    @Autowired
    private DoctorsRepository repository;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid CreateDoctorRequest data, UriComponentsBuilder uriBuilder) {
        var doctor = new Doctor(data);
        repository.save(doctor);

        var uri = uriBuilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new UpdateDoctorResponse(doctor));
    }

    @GetMapping
    // O Pageable é responsável pela paginação.
    public ResponseEntity<Page<GetAllDoctorsResponse>> getAll(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        var response = repository.findAllByActiveTrue(pagination).map(GetAllDoctorsResponse::new);
         return ResponseEntity.ok(response);
    }
    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateDoctorRequest data) {
        var doctor = repository.getReferenceById(data.id());
        doctor.update(data);

        return ResponseEntity.ok(new UpdateDoctorResponse(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        doctor.deleteOne();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        var doctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new UpdateDoctorResponse(doctor));
    }
}
