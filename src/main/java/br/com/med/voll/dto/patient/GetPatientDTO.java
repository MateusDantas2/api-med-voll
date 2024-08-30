package br.com.med.voll.dto.patient;

import br.com.med.voll.domain.entity.Patient;

/**
 * @author Mateus Dantas
 */
public record GetPatientDTO(Long id, String nome, String email, String cpf) {
    public GetPatientDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
