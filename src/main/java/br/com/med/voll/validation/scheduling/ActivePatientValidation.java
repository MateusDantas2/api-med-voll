package br.com.med.voll.validation.scheduling;

import br.com.med.voll.domain.repository.PatienteRepository;
import br.com.med.voll.dto.consultation.ConsultationSchedulingDTO;
import br.com.med.voll.exception.ValidationException;
import org.springframework.stereotype.Component;

/**
 * @author Mateus Dantas
 */
@Component
public class ActivePatientValidation {

    private final PatienteRepository patienteRepository;

    public ActivePatientValidation(PatienteRepository patienteRepository) {
        this.patienteRepository = patienteRepository;
    }

    public void validateDoctorActive(ConsultationSchedulingDTO schedulingDTO) {
        var statusPatient = patienteRepository.findStatusById(schedulingDTO.idPaciente());
        if (!statusPatient) {
            throw new ValidationException("Consulta não pode ser agendada com paciente excluído!");
        }
    }
}
