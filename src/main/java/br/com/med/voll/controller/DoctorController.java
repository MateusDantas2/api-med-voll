package br.com.med.voll.controller;

import br.com.med.voll.domain.entity.Doctor;
import br.com.med.voll.domain.repository.DoctorRepository;
import br.com.med.voll.dto.doctor.DoctorDetailingDTO;
import br.com.med.voll.dto.doctor.DoctorRegisterDTO;
import br.com.med.voll.dto.doctor.DoctorUpdateDTO;
import br.com.med.voll.dto.doctor.GetDoctorDTO;
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
@RequestMapping("/medicos")
public class DoctorController {

    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DoctorRegisterDTO registerDTO, UriComponentsBuilder uriBuilder) {
        Doctor doctor = new Doctor(registerDTO);
        doctorRepository.save(doctor);
        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DoctorDetailingDTO(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<GetDoctorDTO>> getDoctor(@PageableDefault(size = 10, sort = {"crm"}) Pageable pageable) {
        var page = doctorRepository.findAllByStatusTrue(pageable).map(GetDoctorDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DoctorUpdateDTO doctorUpdateDTO) {
        Doctor doctor = doctorRepository.getReferenceById(doctorUpdateDTO.id());
        doctor.updateInformation(doctorUpdateDTO);
        return ResponseEntity.ok(new DoctorDetailingDTO(doctor));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity changeStatus(@PathVariable Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.delete();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        return ResponseEntity.ok(new DoctorDetailingDTO(doctor));
    }
}
