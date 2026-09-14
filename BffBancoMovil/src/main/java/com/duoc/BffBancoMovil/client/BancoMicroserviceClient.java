package com.duoc.BffBancoMovil.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.duoc.BffBancoMovil.model.EstadoCuenta;
import com.duoc.BffBancoMovil.model.MovimientoCuenta;
import com.duoc.BffBancoMovil.model.TransferenciaRequest;

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

}
