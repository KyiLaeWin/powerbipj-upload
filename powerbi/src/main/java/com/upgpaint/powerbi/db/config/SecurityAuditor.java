package com.upgpaint.powerbi.db.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class SecurityAuditor implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {

        return Optional.of(1L);
    }

}
