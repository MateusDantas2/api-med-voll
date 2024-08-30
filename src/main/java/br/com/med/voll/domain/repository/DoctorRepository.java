package br.com.med.voll.domain.repository;

import br.com.med.voll.domain.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Mateus Dantas
 */
public interface DoctorRepository extends JpaRepository<Doctor, Long> {}
