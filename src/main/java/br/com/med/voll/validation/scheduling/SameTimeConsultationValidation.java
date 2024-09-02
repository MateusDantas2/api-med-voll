package br.com.med.voll.validation.scheduling;

import br.com.med.voll.domain.repository.ConsultationRepository;
import br.com.med.voll.dto.consultation.ConsultationSchedulingDTO;
import br.com.med.voll.exception.ValidationException;
import org.springframework.stereotype.Component;

/**
 * @author Mateus Dantas
 */
@Component
public class SameTimeConsultationValidation {

    private final ConsultationRepository consultationRepository;

    public SameTimeConsultationValidation(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    public void validateSameTimeConsultation(ConsultationSchedulingDTO schedulingDTO) {
        var doctorSameTime = consultationRepository.existsByDoctorIdAndDate(schedulingDTO.idMedico(), schedulingDTO.data());
        if (doctorSameTime) {
            throw new ValidationException("Médico já poussuí uma consulta agendada nesse mesmo horário!");
        }
    }
}
