package br.com.med.voll.domain.repository;

import br.com.med.voll.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

/**
 * @author Mateus Dantas
 */
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);
}