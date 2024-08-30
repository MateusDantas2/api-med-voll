package br.com.med.voll.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * @author Mateus Dantas
 */
public record LoginRequestDTO(
        @Size(max = 100, message = "O e-mail deve ter até 100 caracteres.")
        @NotBlank(message = "E-mail é obrigatório.")
        @Email(message = "E-mail inválido.")
        String email,
        @NotBlank(message = "Senha é obrigatória.")
        String password) {
}
