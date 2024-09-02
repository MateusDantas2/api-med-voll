package br.com.med.voll.validation.scheduling;

import br.com.med.voll.dto.consultation.ConsultationSchedulingDTO;
import br.com.med.voll.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

/**
 * @author Mateus Dantas
 */
@Component
public class AdvancedTimeValidation {

    public void validateopeningHours(ConsultationSchedulingDTO schedulingDTO) {
        var consultationDate = schedulingDTO.data();

        boolean sunday = consultationDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean beforeClinicOpen = consultationDate.getHour() < 7;
        boolean afterClinicClose = consultationDate.getHour() > 18;

        if (sunday || beforeClinicOpen || afterClinicClose) {
            throw new ValidationException("Consulta fora do horário de funcionamento!");
        }
    }
}
