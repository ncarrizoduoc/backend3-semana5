package com.duoc.BffBancoCajero.client;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.duoc.BffBancoCajero.model.EstadoCuenta;
import com.duoc.BffBancoCajero.model.MovimientoCuenta;
import com.duoc.BffBancoCajero.security.util.JwtUtil;

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

    public MovimientoCuenta realizarMovimiento(MovimientoCuenta movimiento){
        return restClient.post()
            .uri("/api/banco/movimientos")
            .contentType(MediaType.APPLICATION_JSON)
            .body(movimiento)
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
