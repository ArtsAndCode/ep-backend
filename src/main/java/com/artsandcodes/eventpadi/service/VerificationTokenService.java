package com.artsandcodes.eventpadi.service;

import com.swifta.omnibranches.model.VerificationToken;
import com.swifta.omnibranches.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by prestige on 12/14/2017.
 */

@Service
public class VerificationTokenService {

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    public void save(VerificationToken verificationToken){

        this.verificationTokenRepository.save(verificationToken);
    }

    public VerificationToken findByToken(String token){

        return this.verificationTokenRepository.findVerificationTokenByToken(token);
    }
}
