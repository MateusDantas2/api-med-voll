package br.com.med.voll.dto.patient;

import br.com.med.voll.dto.address.AddressDTO;
import jakarta.validation.constraints.NotNull;

/**
 * @author Mateus Dantas
 */
public record PatientUpdateDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        AddressDTO endereco
) {
}
