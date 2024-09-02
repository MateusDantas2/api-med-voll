package br.com.med.voll.controller.consultation;

import br.com.med.voll.dto.consultation.ConsultationDetailDTO;
import br.com.med.voll.dto.consultation.ConsultationSchedulingDTO;
import br.com.med.voll.service.consultation.ConsultationService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mateus Dantas
 */
@RestController
@RequestMapping("/consultas")
public class ConsultationController {

    private final ConsultationService consultationService;

    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity scheduleConsultations(@RequestBody @Valid ConsultationSchedulingDTO consultation) {
        consultationService.scheduleConsultations(consultation);
//        Doctor doctor = new Doctor(registerDTO);
//        doctorRepository.save(doctor);
//        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.ok(new ConsultationDetailDTO(null, null, null, null));
    }
}
