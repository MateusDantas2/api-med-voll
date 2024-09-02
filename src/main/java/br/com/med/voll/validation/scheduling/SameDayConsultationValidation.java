package br.com.med.voll.validation.scheduling;

import br.com.med.voll.domain.repository.ConsultationRepository;
import br.com.med.voll.dto.consultation.ConsultationSchedulingDTO;
import br.com.med.voll.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Mateus Dantas
 */
@Component
public class SameDayConsultationValidation {

    private final ConsultationRepository consultationRepository;

    public SameDayConsultationValidation(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    private void validateSameDayConsultation(ConsultationSchedulingDTO schedulingDTO) {
        LocalDateTime firstHour = schedulingDTO.data().withHour(7);
        LocalDateTime lastHour = schedulingDTO.data().withHour(18);
        var sameDayConsultation = consultationRepository.existsByPatientIdAndDateBetween(schedulingDTO.idPaciente(), firstHour, lastHour);
        if (sameDayConsultation) {
            throw new ValidationException("Paciente já possuí uma consulta agendada nessse dia!");
        }
    }
}
