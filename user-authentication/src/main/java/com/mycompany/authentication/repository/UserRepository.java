
package com.mycompany.authentication.repository;

import com.mycompany.authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
/**
 * * @author Noble Sebastian.
 * @version 1.0.1.0
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);
}
