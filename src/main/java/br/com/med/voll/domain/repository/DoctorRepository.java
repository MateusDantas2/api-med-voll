package br.com.med.voll.domain.repository;

import br.com.med.voll.domain.entity.Doctor;
import br.com.med.voll.enumaration.Specialty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

/**
 * @author Mateus Dantas
 */
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByStatusTrue(Pageable pageable);

    @Query("""
            SELECT d FROM Doctor d
            WHERE d.status = true
            AND d.especialidade = :specialty
            AND
            d.id NOT IN(
                SELECT c.medico.id FROM Consultation c
                WHERE
                c.data = :data
            )
            ORDER BY RAND()
            LIMIT 1
            """)
    Doctor chooseFreeRandomDoctorOnDate(Specialty specialty, LocalDateTime data);

    @Query(" SELECT d.status FROM Doctor d WHERE d.id = :idDoctor")
    Boolean findStatusById(Long idDoctor);
}