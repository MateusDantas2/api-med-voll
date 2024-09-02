package br.com.med.voll.dto.consultation;

import br.com.med.voll.enumaration.Specialty;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * @author Mateus Dantas
 */
public record ConsultationSchedulingDTO(
        @NotNull
        Long idMedico,
        @NotNull
        Long idPaciente,
        @NotNull
        @Future
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,
        Specialty especialidade
) {
}
