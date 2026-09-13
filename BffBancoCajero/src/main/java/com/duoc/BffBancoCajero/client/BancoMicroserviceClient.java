package com.duoc.BffBancoCajero.client;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.duoc.BffBancoCajero.model.EstadoCuenta;
import com.duoc.BffBancoCajero.model.MovimientoCuenta;

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

    public MovimientoCuenta realizarMovimiento(MovimientoCuenta movimiento){
        return restClient.post()
            .uri("/api/banco/movimientos")
            .contentType(MediaType.APPLICATION_JSON)
            .body(movimiento)
            .retrieve()
            .body(MovimientoCuenta.class);
    }

}
