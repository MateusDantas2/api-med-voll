package br.com.med.voll.controller;

import br.com.med.voll.domain.entity.Patient;
import br.com.med.voll.domain.repository.PatienteRepository;
import br.com.med.voll.dto.patient.GetPatientDTO;
import br.com.med.voll.dto.patient.PatientRegisterDTO;
import br.com.med.voll.dto.patient.PatientUpdateDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mateus Dantas
 */
@RestController
@RequestMapping("/patients")
public class PatientController {

    private final PatienteRepository patienteRepository;

    public PatientController(PatienteRepository patienteRepository) {
        this.patienteRepository = patienteRepository;
    }

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid PatientRegisterDTO dados) {
        patienteRepository.save(new Patient(dados));
    }

    @GetMapping
    public Page<GetPatientDTO> getPatient(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return patienteRepository.findAllByStatusTrue(pageable).map(GetPatientDTO::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid PatientUpdateDTO dados) {
        Patient patient = patienteRepository.getReferenceById(dados.id());
        patient.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var paciente = patienteRepository.getReferenceById(id);
        paciente.delete();
    }
}
