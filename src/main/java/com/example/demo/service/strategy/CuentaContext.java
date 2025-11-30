package com.example.demo.service.strategy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.demo.entity.Cuenta;

@Component
public class CuentaContext {

    private final Map<Cuenta.TipoCuenta, CuentaStrategy> estrategias;

    public CuentaContext(List<CuentaStrategy> estrategiasList) {
        estrategias = estrategiasList.stream()
                .collect(Collectors.toMap(CuentaStrategy::getTipoCuenta, e -> e));
    }

    public void actualizarSaldo(Cuenta cuenta, BigDecimal monto) {
        CuentaStrategy strategy = estrategias.get(cuenta.getTipoCuenta());
        if (strategy == null) {
            throw new RuntimeException("Tipo de cuenta no soportado");
        }
        strategy.actualizarSaldo(cuenta, monto);
    }
}
