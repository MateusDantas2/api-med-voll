package br.com.med.voll.controller;

import br.com.med.voll.domain.entity.Doctor;
import br.com.med.voll.domain.repository.DoctorRepository;
import br.com.med.voll.dto.doctor.DoctorRegisterDTO;
import br.com.med.voll.dto.doctor.DoctorUpdateDTO;
import br.com.med.voll.dto.doctor.GetDoctorDTO;
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
@RequestMapping("/medicos")
public class DoctorController {

    private final DoctorRepository doctorRepository;

    public DoctorController(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DoctorRegisterDTO registerDTO) {
        doctorRepository.save(new Doctor(registerDTO));
    }

    @GetMapping
    public Page<GetDoctorDTO> getDoctor(@PageableDefault(size = 10, sort = {"crm"}) Pageable pageable) {
        return doctorRepository.findAllByStatusTrue(pageable)
                .map(GetDoctorDTO::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DoctorUpdateDTO doctorUpdateDTO) {
        Doctor doctor = doctorRepository.getReferenceById(Long.valueOf(doctorUpdateDTO.id()));
        doctor.updateInformation(doctorUpdateDTO);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void changeStatus(@PathVariable Long id) {
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.delete();
    }
}
