package br.com.med.voll.dto.patient;

import br.com.med.voll.domain.entity.Address;
import br.com.med.voll.domain.entity.Patient;

/**
 * @author Mateus Dantas
 */
public record PatientDetailingDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        String telefone,
        Boolean status,
        Address endereco
) {

    public PatientDetailingDTO(Patient patient) {
        this(patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getCpf(),
                patient.getPhone(),
                patient.getStatus(),
                patient.getAddress()
        );
    }
}
