package com.example.demo.service.strategy;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Cuenta;
import com.example.demo.entity.Transaccion;
import com.example.demo.port.out.CuentaRepositoryPort;

@Component
public class TransferenciaStrategy implements TransaccionStrategy {

    private final CuentaRepositoryPort cuentaRepositoryPort;

    public TransferenciaStrategy(CuentaRepositoryPort cuentaRepositoryPort) {
        this.cuentaRepositoryPort = cuentaRepositoryPort;
    }

    @Override
    public void ejecutar(Transaccion transaccion) {
        Cuenta origen = transaccion.getCuentaOrigen();
        Cuenta destino = transaccion.getCuentaDestino();

        if (origen.getSaldo().compareTo(transaccion.getMonto()) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        origen.setSaldo(origen.getSaldo().subtract(transaccion.getMonto()));
        destino.setSaldo(destino.getSaldo().add(transaccion.getMonto()));

        cuentaRepositoryPort.save(origen);
        cuentaRepositoryPort.save(destino);
    }
}
