package br.com.med.voll.controller;

import br.com.med.voll.dto.MedicalRegistrationDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mateus Dantas
 */
@RestController
@RequestMapping("/medicos")
public class DoctorController {

    @PostMapping
    public void register(@RequestBody MedicalRegistrationDTO registration) {
        System.out.println(registration);
    }
}
