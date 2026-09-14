package com.duoc.BffBancoMovil.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String secret;
    private long expiration; // milliseconds
    private String serviceSecret;
    private long serviceExpiration; // milliseconds

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }

    public String getServiceSecret() {
        return serviceSecret;
    }

    public void setServiceSecret(String serviceSecret) {
        this.serviceSecret = serviceSecret;
    }

    public long getServiceExpiration() {
        return serviceExpiration;
    }

    public void setServiceExpiration(long serviceExpiration) {
        this.serviceExpiration = serviceExpiration;
    }
}
