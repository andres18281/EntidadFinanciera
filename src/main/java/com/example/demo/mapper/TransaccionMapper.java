package com.example.demo.mapper;

import com.example.demo.dto.CrearTransaccionRequest;
import com.example.demo.dto.TransaccionResponse;
import com.example.demo.entity.Cuenta;
import com.example.demo.entity.Transaccion;

import java.time.LocalDateTime;


public class TransaccionMapper {

    public static Transaccion toEntity(CrearTransaccionRequest request, Cuenta origen, Cuenta destino) {

        Transaccion.TipoTransaccion tipoEnum =
                Transaccion.TipoTransaccion.valueOf(request.getTipoTransaccion().toUpperCase());

        return Transaccion.builder()
                .tipoTransaccion(tipoEnum)  
                .monto(request.getMonto())
                .cuentaOrigen(origen)
                .cuentaDestino(destino)
                .fechaTransaccion(LocalDateTime.now())
                .exitosa(true)
                .build();
    }

    public static TransaccionResponse toResponse(Transaccion transaccion) {
        TransaccionResponse resp = new TransaccionResponse();
        resp.setId(transaccion.getId());
        resp.setTipoTransaccion(transaccion.getTipoTransaccion().name());
        resp.setMonto(transaccion.getMonto());
        resp.setFechaTransaccion(transaccion.getFechaTransaccion());
        resp.setExitosa(transaccion.getExitosa());
        resp.setCuentaOrigenId(
                transaccion.getCuentaOrigen() != null ? transaccion.getCuentaOrigen().getId() : null
        );
        resp.setCuentaDestinoId(
                transaccion.getCuentaDestino() != null ? transaccion.getCuentaDestino().getId() : null
        );
        return resp;
    }
}

