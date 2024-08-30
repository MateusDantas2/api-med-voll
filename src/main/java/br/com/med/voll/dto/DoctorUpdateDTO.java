package br.com.med.voll.dto;

import br.com.med.voll.enumaration.Specialty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * @author Mateus Dantas
 */
public record DoctorUpdateDTO(
        @NotNull
        Integer id,
        String nome,
        String telefone,
        @Valid AddressDTO endereco
) {
}
