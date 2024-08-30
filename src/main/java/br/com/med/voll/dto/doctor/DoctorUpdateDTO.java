package br.com.med.voll.dto.doctor;

import br.com.med.voll.dto.address.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

/**
 * @author Mateus Dantas
 */
public record DoctorUpdateDTO(
        @NotNull
        Long id,
        String nome,
        String telefone,
        @Valid AddressDTO endereco
) {
}
