package br.com.med.voll.validation.scheduling;

import br.com.med.voll.dto.consultation.ConsultationSchedulingDTO;
import br.com.med.voll.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;

/**
 * @author Mateus Dantas
 */
@Component
public class OpeningHoursValidation {

    public void validateAdvancedTimeHours(ConsultationSchedulingDTO schedulingDTO) {
        var consultationDate = schedulingDTO.data();

        LocalDate now = LocalDate.now();
        long differenceInMinutes = Duration.between(now, consultationDate).toMinutes();

        if (differenceInMinutes < 30) {
            throw new ValidationException("Consulta deve ser agendada com antecedência mínima de 30 minutos!");
        }
    }
}
