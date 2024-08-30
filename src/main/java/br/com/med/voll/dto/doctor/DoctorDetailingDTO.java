package br.com.med.voll.dto.doctor;

import br.com.med.voll.domain.entity.Address;
import br.com.med.voll.domain.entity.Doctor;
import br.com.med.voll.enumaration.Specialty;

/**
 * @author Mateus Dantas
 */
public record DoctorDetailingDTO(
        Long id,
        String nome,
        String email,
        String crm,
        String telefone,
        Specialty especialidade,
        Boolean status,
        Address endereco
) {

    public DoctorDetailingDTO(Doctor doctor) {
        this(doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getCrm(),
                doctor.getPhone(),
                doctor.getSpecialty(),
                doctor.getStatus(),
                doctor.getAddress()
        );
    }
}
