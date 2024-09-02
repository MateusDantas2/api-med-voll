package br.com.med.voll.service.consultation;

import br.com.med.voll.domain.entity.Consultation;
import br.com.med.voll.domain.entity.Doctor;
import br.com.med.voll.domain.entity.Patient;
import br.com.med.voll.domain.repository.ConsultationRepository;
import br.com.med.voll.domain.repository.DoctorRepository;
import br.com.med.voll.domain.repository.PatienteRepository;
import br.com.med.voll.dto.consultation.ConsultationSchedulingDTO;
import br.com.med.voll.exception.ValidationException;
import org.springframework.stereotype.Service;

/**
 * @author Mateus Dantas
 */
@Service
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final DoctorRepository doctorRepository;
    private final PatienteRepository patienteRepository;

    public ConsultationService(ConsultationRepository consultationRepository, DoctorRepository doctorRepository, PatienteRepository patienteRepository) {
        this.consultationRepository = consultationRepository;
        this.doctorRepository = doctorRepository;
        this.patienteRepository = patienteRepository;
    }

    public void scheduleConsultations(ConsultationSchedulingDTO consultationDTO) {
        if (!doctorRepository.existsById(consultationDTO.idMedico())) {
            throw new ValidationException("Id do médico informado não existe!");
        }
        if (consultationDTO.idMedico() != null && !patienteRepository.existsById(consultationDTO.idPaciente())) {
            throw new ValidationException("Id do paciente informado não existe!");
        }
        var doctor = chooseDoctor(consultationDTO);
        Patient patient = patienteRepository.getReferenceById(consultationDTO.idPaciente());
        Consultation consultation = new Consultation(null, doctor, patient, consultationDTO.data());
        consultationRepository.save(consultation);
    }

    private Doctor chooseDoctor(ConsultationSchedulingDTO consultationDTO) {
        if (consultationDTO.idMedico() != null) {
            return doctorRepository.getReferenceById(consultationDTO.idMedico());
        }

        if (consultationDTO.especialidade() == null) {
            throw new ValidationException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return doctorRepository.chooseFreeRandomDoctorOnDate(consultationDTO.especialidade(), consultationDTO.data());
    }
}
