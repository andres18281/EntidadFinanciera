package com.example.demo.service.strategy;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Cuenta;

@Component
public class CorrienteCuentaStrategy implements CuentaStrategy {

    @Override
    public Cuenta.TipoCuenta getTipoCuenta() {
        return Cuenta.TipoCuenta.CORRIENTE;
    }

    @Override
    public void actualizarSaldo(Cuenta cuenta, BigDecimal monto) {
        // Regla: permitir saldo negativo hasta -5000 (ejemplo)
        BigDecimal nuevoSaldo = cuenta.getSaldo().add(monto);
        if (nuevoSaldo.compareTo(new BigDecimal("-5000")) < 0) {
            throw new RuntimeException("Saldo insuficiente en cuenta corriente");
        }
        cuenta.setSaldo(nuevoSaldo);
    }
}