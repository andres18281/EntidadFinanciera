package com.example.demo.service.strategy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Transaccion;

@Component
public class TransaccionContext {

    private final Map<Transaccion.TipoTransaccion, TransaccionStrategy> estrategias;

    public TransaccionContext(List<TransaccionStrategy> estrategiasList) {
        estrategias = estrategiasList.stream()
            .collect(Collectors.toMap(
                estrategia -> {
                    if (estrategia instanceof ConsignacionStrategy) return Transaccion.TipoTransaccion.CONSIGNACION;
                    if (estrategia instanceof RetiroStrategy) return Transaccion.TipoTransaccion.RETIRO;
                    if (estrategia instanceof TransferenciaStrategy) return Transaccion.TipoTransaccion.TRANSFERENCIA;
                    throw new RuntimeException("Estrategia desconocida");
                },
                estrategia -> estrategia
            ));
    }

    public void ejecutar(Transaccion transaccion) {
        TransaccionStrategy strategy = estrategias.get(transaccion.getTipoTransaccion());
        if (strategy == null) {
            throw new RuntimeException("Tipo de transacción no soportado");
        }
        strategy.ejecutar(transaccion);
    }
}
