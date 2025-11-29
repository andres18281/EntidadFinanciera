package com.example.demo.service.strategy;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Cuenta;
import com.example.demo.entity.Transaccion;
import com.example.demo.port.out.CuentaRepositoryPort;

@Component
public class ConsignacionStrategy implements TransaccionStrategy {

    private final CuentaRepositoryPort cuentaRepositoryPort;

    public ConsignacionStrategy(CuentaRepositoryPort cuentaRepositoryPort) {
        this.cuentaRepositoryPort = cuentaRepositoryPort;
    }

    @Override
    public void ejecutar(Transaccion transaccion) {
        Cuenta cuentaOrigen = transaccion.getCuentaOrigen();
        cuentaOrigen.setSaldo(cuentaOrigen.getSaldo().add(transaccion.getMonto()));
        cuentaRepositoryPort.save(cuentaOrigen);
    }
}