package med.voll.medapi.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import med.voll.medapi.domains.appointment.dtos.createAppointmentDTO;
import med.voll.medapi.domains.appointment.useCases.GetAllAppointmentsByDoctorUseCase;
import med.voll.medapi.domains.appointment.useCases.ScheduleAppointmentsUseCase;
import med.voll.medapi.domains.doctor.dtos.GetAllAppointmentsByDoctorIdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private ScheduleAppointmentsUseCase scheduleAppointmentsUseCase;

    @Autowired
    GetAllAppointmentsByDoctorUseCase getAllAppointmentsByDoctorUseCase;

    @PostMapping
    public ResponseEntity create(@RequestBody @Valid createAppointmentDTO data, HttpServletRequest request) {
        String username = (String) request.getAttribute("userSubject");
        var response = scheduleAppointmentsUseCase.execute(data, username);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all/doctor/{doctorId}")
    public ResponseEntity getAllByDoctorId(@PathVariable Long doctorId) {
        var response = getAllAppointmentsByDoctorUseCase.execute(doctorId.toString());

        return ResponseEntity.ok(response);
    }
}
