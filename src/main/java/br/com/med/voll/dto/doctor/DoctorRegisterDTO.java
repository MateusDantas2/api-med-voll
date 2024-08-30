package br.com.med.voll.dto.doctor;

import br.com.med.voll.dto.address.AddressDTO;
import br.com.med.voll.enumaration.Specialty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 * @author Mateus Dantas
 */
public record DoctorRegisterDTO(
        @NotBlank
        String nome,
        @Email
        @NotBlank
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,
        @NotNull
        Specialty especialidade,
        @NotNull
        @Valid AddressDTO endereco
) {
}
