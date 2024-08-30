package br.com.med.voll.controller;

import br.com.med.voll.domain.entity.Doctor;
import br.com.med.voll.domain.repository.DoctorRepository;
import br.com.med.voll.dto.DoctorRegisterDTO;
import br.com.med.voll.dto.DoctorUpdateDTO;
import br.com.med.voll.dto.GetDoctorDTO;
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
        var doctor = doctorRepository.getReferenceById(Long.valueOf(doctorUpdateDTO.id()));
        doctor.updateInformation(doctorUpdateDTO);
    }

//    @Transactional
//    @DeleteMapping("/{id}")
//    public void delete(@PathVariable Long id) {
//        doctorRepository.deleteById(id);
//    }

    @Transactional
    @DeleteMapping("/{id}")
    public void changeStatus(@PathVariable Long id) {
        var medico = doctorRepository.getReferenceById(id);
        medico.delete();
    }
}
