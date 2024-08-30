package br.com.med.voll.controller;

import br.com.med.voll.domain.entity.Patient;
import br.com.med.voll.domain.repository.PatienteRepository;
import br.com.med.voll.dto.patient.GetPatientDTO;
import br.com.med.voll.dto.patient.PatientDetailingDTO;
import br.com.med.voll.dto.patient.PatientRegisterDTO;
import br.com.med.voll.dto.patient.PatientUpdateDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @author Mateus Dantas
 */
@RestController
@RequestMapping("/pacientes")
public class PatientController {

    private final PatienteRepository patienteRepository;

    public PatientController(PatienteRepository patienteRepository) {
        this.patienteRepository = patienteRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PatientRegisterDTO dados, UriComponentsBuilder uriBuilder) {
        Patient patient = new Patient(dados);
        patienteRepository.save(patient);
        URI uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientDetailingDTO(patient));
    }

    @GetMapping
    public ResponseEntity<Page<GetPatientDTO>> getPatient(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        var page = patienteRepository.findAllByStatusTrue(pageable).map(GetPatientDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid PatientUpdateDTO dados) {
        Patient patient = patienteRepository.getReferenceById(dados.id());
        patient.atualizarInformacoes(dados);
        return ResponseEntity.ok(new PatientDetailingDTO(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity changeStatus(@PathVariable Long id) {
        var paciente = patienteRepository.getReferenceById(id);
        paciente.delete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getPatientById(@PathVariable Long id) {
        var paciente = patienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new PatientDetailingDTO(paciente));
    }
}
