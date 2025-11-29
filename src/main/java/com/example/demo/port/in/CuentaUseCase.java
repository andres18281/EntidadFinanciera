package com.example.demo.port.in;


import com.example.demo.entity.Cuenta;
import java.util.List;

public interface CuentaUseCase {
    Cuenta createCuenta(Cuenta cuenta);
    List<Cuenta> getCuentasByClienteId(Long clienteId);
    void actualizarSaldo(Long cuentaId, double monto);
}