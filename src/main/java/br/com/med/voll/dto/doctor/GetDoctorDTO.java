package br.com.med.voll.dto.doctor;

import br.com.med.voll.domain.entity.Doctor;
import br.com.med.voll.enumaration.Specialty;

/**
 * @author Mateus Dantas
 */
public record GetDoctorDTO(Long id, String nome, String email, String crm, Specialty especialidade) {

    public GetDoctorDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
