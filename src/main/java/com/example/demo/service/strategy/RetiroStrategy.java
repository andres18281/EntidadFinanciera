package com.example.demo.service.strategy;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Cuenta;
import com.example.demo.entity.Transaccion;
import com.example.demo.port.out.CuentaRepositoryPort;

@Component
public class RetiroStrategy implements TransaccionStrategy {

    private final CuentaRepositoryPort cuentaRepositoryPort;

    public RetiroStrategy(CuentaRepositoryPort cuentaRepositoryPort) {
        this.cuentaRepositoryPort = cuentaRepositoryPort;
    }

    @Override
    public Transaccion.TipoTransaccion getTipo() {
        return Transaccion.TipoTransaccion.RETIRO;
    }

    @Override
    public void ejecutar(Transaccion transaccion) {
        Cuenta cuentaOrigen = transaccion.getCuentaOrigen();

        if (cuentaOrigen == null) {
            throw new RuntimeException("El retiro requiere una cuenta origen");
        }

        if (cuentaOrigen.getSaldo().compareTo(transaccion.getMonto()) < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        cuentaOrigen.setSaldo(
                cuentaOrigen.getSaldo().subtract(transaccion.getMonto())
        );

        cuentaRepositoryPort.save(cuentaOrigen);
    }
}
