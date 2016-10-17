package ru.knize.hyperloop.config;

/**
 * Created by knize on 06.10.16.
 */

import org.springframework.security.web.context.*;

public class SecurityWebApplicationInitializer
        extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer() {
        super(SecurityConfig.class);
    }
}