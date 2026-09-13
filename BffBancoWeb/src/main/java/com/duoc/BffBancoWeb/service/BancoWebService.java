package com.duoc.BffBancoWeb.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.BffBancoWeb.client.BancoMicroserviceClient;
import com.duoc.BffBancoWeb.model.EstadoCuenta;
import com.duoc.BffBancoWeb.model.MovimientoCuenta;
import com.duoc.BffBancoWeb.model.TransferenciaRequest;

@Service 
public class BancoWebService {

    private final BancoMicroserviceClient bancoMicroserviceClient;

    public BancoWebService(BancoMicroserviceClient bancoMicroserviceClient) {
        this.bancoMicroserviceClient = bancoMicroserviceClient;
    }

    public EstadoCuenta getEstadoCuenta(Long id){
        EstadoCuenta estadoCuenta = bancoMicroserviceClient.getEstadoCuenta(id);
        if (estadoCuenta == null){
            return null;
        }
        return estadoCuenta;
    }

    public MovimientoCuenta realizarTransferencia(TransferenciaRequest request) {
        return bancoMicroserviceClient.realizarTransferencia(request);
    }

    public MovimientoCuenta realizarMovimiento(MovimientoCuenta movimiento) {
        return bancoMicroserviceClient.realizarMovimiento(movimiento);
    }

    public List<MovimientoCuenta> verMovimientos(String tipoMovimiento, LocalDate fecha) {
        return bancoMicroserviceClient.verMovimientos(tipoMovimiento, fecha);
    }

}
