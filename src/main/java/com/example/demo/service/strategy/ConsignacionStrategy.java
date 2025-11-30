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
    public Transaccion.TipoTransaccion getTipo() {
        return Transaccion.TipoTransaccion.CONSIGNACION;
    }

    @Override
    public void ejecutar(Transaccion transaccion) {
        Cuenta cuentaDestino = transaccion.getCuentaDestino();

        if (cuentaDestino == null) {
            throw new RuntimeException("La consignación requiere una cuenta destino");
        }

        cuentaDestino.setSaldo(
                cuentaDestino.getSaldo().add(transaccion.getMonto())
        );

        cuentaRepositoryPort.save(cuentaDestino);
    }
}
