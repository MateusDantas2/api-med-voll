package br.com.med.voll.domain.repository;

import br.com.med.voll.domain.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Mateus Dantas
 */
public interface PatienteRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findAllByStatusTrue(Pageable paginacao);
}
