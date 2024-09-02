package br.com.med.voll.validation.scheduling;

import br.com.med.voll.domain.repository.DoctorRepository;
import br.com.med.voll.dto.consultation.ConsultationSchedulingDTO;
import br.com.med.voll.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDate;

/**
 * @author Mateus Dantas
 */
@Component
public class ActiveDoctorValidation {

    private final DoctorRepository doctorRepository;

    public ActiveDoctorValidation(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void validateDoctorActive(ConsultationSchedulingDTO schedulingDTO) {
        if (schedulingDTO.idMedico() == null) {
            return;
        }

        Boolean doctorActive = doctorRepository.findStatusById(schedulingDTO.idMedico());
        if (!doctorActive) {
            throw new ValidationException("Consulta não pode ser agendada com médico excluído!");
        }
    }
}
