package com.artsandcodes.eventpadi.repository;

import com.artsandcodes.eventpadi.model.VerificationToken;
import org.springframework.stereotype.Repository;

/**
 * Created by prestige on 1/19/2018.
 */

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {


    VerificationToken findVerificationTokenByToken(String token);
}
