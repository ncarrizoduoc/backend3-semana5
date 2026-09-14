package com.duoc.BffBancoMovil.client;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.duoc.BffBancoMovil.model.EstadoCuenta;
import com.duoc.BffBancoMovil.model.MovimientoCuenta;
import com.duoc.BffBancoMovil.model.TransferenciaRequest;
import com.duoc.BffBancoMovil.security.util.JwtUtil;

@Component
public class BancoMicroserviceClient {
    private final RestClient restClient;
    private final JwtUtil jwtUtil;

    public BancoMicroserviceClient(RestClient restClient, JwtUtil jwtUtil) {
        this.restClient = restClient;
        this.jwtUtil = jwtUtil;
    }

    public EstadoCuenta getEstadoCuenta(Long id){
        return restClient.get()
            .uri("/api/banco/estado-cuenta/{id}", id)
            .header("Authorization", "Bearer " + generateServiceToken())
            .retrieve()
            .body(EstadoCuenta.class);
    }

    public MovimientoCuenta realizarTransferencia(TransferenciaRequest request) {
        return restClient.post()
            .uri("/api/banco/transferencias")
            .body(request)
            .header("Authorization", "Bearer " + generateServiceToken())
            .retrieve()
            .body(MovimientoCuenta.class);
    }

    private String generateServiceToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtil.generateServiceToken(userDetails);
    }

}
