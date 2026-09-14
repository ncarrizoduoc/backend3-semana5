package com.duoc.BffBancoMovil.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.duoc.BffBancoMovil.client.BancoMicroserviceClient;
import com.duoc.BffBancoMovil.model.EstadoCuenta;
import com.duoc.BffBancoMovil.model.MovimientoCuenta;
import com.duoc.BffBancoMovil.model.TransferenciaRequest;

@Service 
public class BancoMovilService {

    private final BancoMicroserviceClient bancoMicroserviceClient;

    public BancoMovilService(BancoMicroserviceClient bancoMicroserviceClient) {
        this.bancoMicroserviceClient = bancoMicroserviceClient;
    }

    public EstadoCuenta getEstadoCuenta(Long id){
        EstadoCuenta estadoCuenta = bancoMicroserviceClient.getEstadoCuenta(id);
        if (estadoCuenta == null){
            return null;
        }

        // Simplificar estado de cuenta para dispositivos moviles
        List<MovimientoCuenta> movimientos = estadoCuenta.getMovimientos();
        
        // Ordenar lista de movimientos por fecha descendente
        movimientos.sort((m1, m2) -> m2.getFecha().compareTo(m1.getFecha()));
        // Mostrar solo ultimos 5 movimientos
        movimientos = movimientos.subList(0, Math.min(5, movimientos.size()));

        estadoCuenta.setMovimientos(movimientos);
        return estadoCuenta;
        
    }

    public MovimientoCuenta realizarTransferencia(TransferenciaRequest request) {
        return bancoMicroserviceClient.realizarTransferencia(request);
    }

}
