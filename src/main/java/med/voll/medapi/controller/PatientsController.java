package med.voll.medapi.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import med.voll.medapi.domains.patients.dtos.CreatePatientControllerRequest;
import med.voll.medapi.domains.patients.PatientsRepository;
import med.voll.medapi.domains.patients.dtos.CreatePatientResponse;
import med.voll.medapi.domains.patients.dtos.CreatePatientUseCaseRequest;
import med.voll.medapi.domains.patients.useCases.CreatePatientUseCase;
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

    @Autowired
    private CreatePatientUseCase createPatientUseCase;

    @PostMapping
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity create(@RequestBody @Valid CreatePatientControllerRequest data, UriComponentsBuilder uriBuilder, HttpServletRequest request) {
        String username = (String) request.getAttribute("userSubject");
        var useCaseData = new CreatePatientUseCaseRequest(data, username);
        var response = createPatientUseCase.execute(useCaseData);

        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(new CreatePatientResponse(response));
    }
}
