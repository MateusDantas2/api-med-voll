package br.com.med.voll.dto;

import br.com.med.voll.domain.entity.Doctor;
import br.com.med.voll.enumaration.Specialty;

/**
 * @author Mateus Dantas
 */
public record GetDoctorDTO(String nome, String email, String crm, Specialty especialidade) {

    public GetDoctorDTO(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
