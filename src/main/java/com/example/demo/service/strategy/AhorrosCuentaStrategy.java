package com.example.demo.service.strategy;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Cuenta;

@Component
public class AhorrosCuentaStrategy implements CuentaStrategy {

    @Override
    public Cuenta.TipoCuenta getTipoCuenta() {
        return Cuenta.TipoCuenta.AHORROS;
    }

    @Override
    public void actualizarSaldo(Cuenta cuenta, BigDecimal monto) {
        // Regla: saldo no puede ser negativo
        BigDecimal nuevoSaldo = cuenta.getSaldo().add(monto);
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Saldo insuficiente en cuenta de ahorros");
        }
        cuenta.setSaldo(nuevoSaldo);
    }
}
