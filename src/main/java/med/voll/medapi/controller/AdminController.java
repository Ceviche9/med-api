package med.voll.medapi.controller;

import med.voll.medapi.domains.doctor.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("secret-admin")
public class AdminController {

    @Autowired
    private DoctorsRepository doctorsRepository;

    @DeleteMapping("/doctors/{id}")
    @Transactional
    public void deleteOne(@PathVariable Long id) {
        doctorsRepository.deleteById(id);
    }

}
