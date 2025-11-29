package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.port.in.CuentaUseCase;
import com.example.demo.port.out.CuentaRepositoryPort;
import com.example.demo.entity.Cuenta;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaService implements CuentaUseCase {

    private final CuentaRepositoryPort cuentaRepositoryPort;

    public CuentaService(CuentaRepositoryPort cuentaRepositoryPort) {
        this.cuentaRepositoryPort = cuentaRepositoryPort;
    }

    @Override
    public Cuenta createCuenta(Cuenta cuenta) {
        // Validaciones de saldo inicial, tipo de cuenta, estado, etc.
        if (cuenta.getTipoCuenta() == Cuenta.TipoCuenta.AHORROS && cuenta.getSaldo().doubleValue() < 0) {
            throw new RuntimeException("Saldo de cuenta de ahorros no puede ser menor a 0");
        }
        return cuentaRepositoryPort.save(cuenta);
    }

    @Override
    public List<Cuenta> getCuentasByClienteId(Long clienteId) {
        return cuentaRepositoryPort.findByClienteId(clienteId);
    }

    @Override
    public void actualizarSaldo(Long cuentaId, double monto) {
        Optional<Cuenta> opt = cuentaRepositoryPort.findById(cuentaId);
        if (opt.isPresent()) {
            Cuenta cuenta = opt.get();
            cuenta.setSaldo(cuenta.getSaldo().add(java.math.BigDecimal.valueOf(monto)));
            cuentaRepositoryPort.save(cuenta);
        } else {
            throw new RuntimeException("Cuenta no encontrada");
        }
    }
}
