
package com.mycompany.authentication.repository;

import com.mycompany.authentication.model.token.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
/**
 * * @author Noble Sebastian.
 * @version 1.0.1.0
 */
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    @Override
    Optional<RefreshToken> findById(Long id);

    Optional<RefreshToken> findByToken(String token);

}
