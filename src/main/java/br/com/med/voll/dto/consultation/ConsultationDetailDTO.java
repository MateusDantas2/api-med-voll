package br.com.med.voll.dto.consultation;

import java.time.LocalDateTime;

/**
 * @author Mateus Dantas
 */
public record ConsultationDetailDTO(Long id, Long idMedico, Long idPaciente, LocalDateTime data) {}
