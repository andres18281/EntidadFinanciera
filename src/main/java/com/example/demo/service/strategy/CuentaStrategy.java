package com.example.demo.service.strategy;

import java.math.BigDecimal;

import com.example.demo.entity.Cuenta;

public interface CuentaStrategy {
    Cuenta.TipoCuenta getTipoCuenta(); // indica a qué tipo de cuenta aplica
    void actualizarSaldo(Cuenta cuenta, BigDecimal monto);
}