package br.com.med.voll.dto;

import br.com.med.voll.enumaration.Specialty;

/**
 * @author Mateus Dantas
 */
public record MedicalRegistrationDTO(String nome, String email, Integer crm, Specialty especialidade, AddressDTO endereco) {}
