package com.duoc.BffBancoWeb.client;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.duoc.BffBancoWeb.model.EstadoCuenta;
import com.duoc.BffBancoWeb.model.MovimientoCuenta;
import com.duoc.BffBancoWeb.model.TransferenciaRequest;

@Component
public class BancoMicroserviceClient {
    private final RestClient restClient;

    public BancoMicroserviceClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public EstadoCuenta getEstadoCuenta(Long id){
        return restClient.get()
            .uri("/api/banco/estado-cuenta/{id}", id)
            .retrieve()
            .body(EstadoCuenta.class);
    }

    public MovimientoCuenta realizarTransferencia(TransferenciaRequest request) {
        return restClient.post()
            .uri("/api/banco/transferencias")
            .body(request)
            .retrieve()
            .body(MovimientoCuenta.class);
    }

    public MovimientoCuenta realizarMovimiento(MovimientoCuenta movimiento) {
        return restClient.post()
            .uri("/api/banco/movimientos")
            .body(movimiento)
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
                .retrieve()
                .body(List.class);
        } else if (tipoMovimiento != null && fecha == null){
            return restClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/api/banco/movimientos")
                    .queryParam("tipoMovimiento", tipoMovimiento)
                    .build())
                .retrieve()
                .body(List.class);
        } else if (tipoMovimiento == null && fecha == null){
            return restClient.get()
                .uri("/api/banco/movimientos")
                .retrieve()
                .body(List.class);
        }
        return restClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/api/banco/movimientos")
                .queryParam("tipoMovimiento", tipoMovimiento)
                .queryParam("fecha", fecha)
                .build())
            .retrieve()
            .body(List.class);
    }

}
