package med.voll.medapi.controller;

import jakarta.validation.Valid;
import med.voll.medapi.domains.appointment.dtos.createAppointmentDTO;
import med.voll.medapi.domains.appointment.useCases.ScheduleAppointmentsUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private ScheduleAppointmentsUseCase scheduleAppointmentsUseCase;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid createAppointmentDTO data) {
        var response = scheduleAppointmentsUseCase.execute(data);
        return ResponseEntity.ok(response);
    }
}
