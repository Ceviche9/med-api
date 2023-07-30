package med.voll.medapi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import med.voll.medapi.domains.patients.CreatePatientRequest;
import med.voll.medapi.domains.patients.Patient;
import med.voll.medapi.domains.patients.PatientsRepository;
import med.voll.medapi.domains.patients.dtos.CreatePatientResponse;
import med.voll.medapi.domains.user.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("patients")
public class PatientsController {
    @Autowired
    private PatientsRepository patientsRepository;
    @Autowired
    private UsersRepository usersRepository;
    @PostMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity create(@RequestBody @Valid CreatePatientRequest data, UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        String username = (String) request.getAttribute("userSubject");
        var user = usersRepository.findByUsername(username);
        // TODO: Relacionar usuário com o paciente.
        var patient = new Patient();
        patientsRepository.save(patient);

        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new CreatePatientResponse(patient));
    }
}
