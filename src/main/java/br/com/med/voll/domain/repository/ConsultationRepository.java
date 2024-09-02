package br.com.med.voll.domain.repository;

import br.com.med.voll.domain.entity.Consultation;
import br.com.med.voll.domain.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

/**
 * @author Mateus Dantas
 */
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime firstHour, LocalDateTime LastHour);

    boolean existsByDoctorIdAndDate(Long idDoctor, LocalDateTime date);
}