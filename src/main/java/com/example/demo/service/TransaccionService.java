package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.port.in.TransaccionUseCase;
import com.example.demo.port.out.TransaccionRepositoryPort;
import com.example.demo.port.out.CuentaRepositoryPort;
import com.example.demo.entity.Transaccion;
import com.example.demo.entity.Cuenta;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TransaccionService implements TransaccionUseCase {

    private final TransaccionRepositoryPort transaccionRepositoryPort;
    private final CuentaRepositoryPort cuentaRepositoryPort;

    public TransaccionService(TransaccionRepositoryPort transaccionRepositoryPort,
                              CuentaRepositoryPort cuentaRepositoryPort) {
        this.transaccionRepositoryPort = transaccionRepositoryPort;
        this.cuentaRepositoryPort = cuentaRepositoryPort;
    }

    @Override
    public Transaccion createTransaccion(Transaccion transaccion) {
        // Obtener la cuenta origen
        Optional<Cuenta> cuentaOrigenOpt = cuentaRepositoryPort.findById(transaccion.getCuentaOrigen().getId());
        if (cuentaOrigenOpt.isEmpty()) {
            throw new RuntimeException("Cuenta origen no encontrada");
        }
        Cuenta cuentaOrigen = cuentaOrigenOpt.get();

        // Obtener la cuenta destino solo si es transferencia
        Cuenta cuentaDestino = null;
        if (transaccion.getTipoTransaccion() == Transaccion.TipoTransaccion.TRANSFERENCIA) {
            Optional<Cuenta> cuentaDestinoOpt = cuentaRepositoryPort.findById(transaccion.getCuentaDestino().getId());
            if (cuentaDestinoOpt.isEmpty()) {
                throw new RuntimeException("Cuenta destino no encontrada para la transferencia");
            }
            cuentaDestino = cuentaDestinoOpt.get();
        }

        // Validaciones y actualización de saldo
        switch (transaccion.getTipoTransaccion()) {
            case RETIRO:
            case TRANSFERENCIA:
                if (cuentaOrigen.getSaldo().compareTo(transaccion.getMonto()) < 0) {
                    throw new RuntimeException("Saldo insuficiente");
                }
                cuentaOrigen.setSaldo(cuentaOrigen.getSaldo().subtract(transaccion.getMonto()));
                break;
            case CONSIGNACION:
                cuentaOrigen.setSaldo(cuentaOrigen.getSaldo().add(transaccion.getMonto()));
                break;
            default:
                throw new RuntimeException("Tipo de transacción no válido");
        }

        // Guardar cuenta origen
        cuentaRepositoryPort.save(cuentaOrigen);

        // Actualizar y guardar cuenta destino si es transferencia
        if (transaccion.getTipoTransaccion() == Transaccion.TipoTransaccion.TRANSFERENCIA) {
            cuentaDestino.setSaldo(cuentaDestino.getSaldo().add(transaccion.getMonto()));
            cuentaRepositoryPort.save(cuentaDestino);
        }

        // Guardar la transacción
        transaccion.setFechaTransaccion(LocalDateTime.now());
        transaccion.setExitosa(true);
        return transaccionRepositoryPort.save(transaccion);
    }
}
