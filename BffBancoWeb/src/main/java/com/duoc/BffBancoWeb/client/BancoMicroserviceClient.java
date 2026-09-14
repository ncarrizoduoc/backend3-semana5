package com.duoc.BffBancoWeb.client;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.duoc.BffBancoWeb.model.EstadoCuenta;
import com.duoc.BffBancoWeb.model.MovimientoCuenta;
import com.duoc.BffBancoWeb.model.TransferenciaRequest;
import com.duoc.BffBancoWeb.security.util.JwtUtil;

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

    public MovimientoCuenta realizarMovimiento(MovimientoCuenta movimiento) {
        return restClient.post()
            .uri("/api/banco/movimientos")
            .body(movimiento)
            .header("Authorization", "Bearer " + generateServiceToken())
            .retrieve()
            .body(MovimientoCuenta.class);
    }

    public List<MovimientoCuenta> verMovimientos(String tipoMovimiento, LocalDate fecha) {
        if (tipoMovimiento == null && fecha != null){
            return restClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/api/banco/movimientos")
                    .queryParam("fecha", fecha)
                    .build())
                .header("Authorization", "Bearer " + generateServiceToken())
                .retrieve()
                .body(List.class);

        } else if (tipoMovimiento != null && fecha == null){
            return restClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/api/banco/movimientos")
                    .queryParam("tipoMovimiento", tipoMovimiento)
                    .build())
                .header("Authorization", "Bearer " + generateServiceToken())
                .retrieve()
                .body(List.class);

        } else if (tipoMovimiento == null && fecha == null){
            return restClient.get()
                .uri("/api/banco/movimientos")
                .header("Authorization", "Bearer " + generateServiceToken())
                .retrieve()
                .body(List.class);

        }
        return restClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/api/banco/movimientos")
                .queryParam("tipoMovimiento", tipoMovimiento)
                .queryParam("fecha", fecha)
                .build())
            .header("Authorization", "Bearer " + generateServiceToken())
            .retrieve()
            .body(List.class);
    }

    private String generateServiceToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtil.generateServiceToken(userDetails);
    }

}
