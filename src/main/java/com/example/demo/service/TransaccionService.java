package com.example.demo.service;


import com.example.demo.entity.Cuenta;
import com.example.demo.entity.Transaccion;
import com.example.demo.port.out.CuentaRepositoryPort;
import com.example.demo.port.out.TransaccionRepositoryPort;
import com.example.demo.service.strategy.TransaccionContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class TransaccionService {

    private final TransaccionRepositoryPort transaccionRepositoryPort;
    private final CuentaRepositoryPort cuentaRepositoryPort;
    private final TransaccionContext transaccionContext;

    // ---------------------------------------------------------
    // CONSIGNAR
    // ---------------------------------------------------------
    public Transaccion consignar(Long cuentaId, BigDecimal monto) {
        Cuenta cuenta = cuentaRepositoryPort.findById(cuentaId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        Transaccion tx = Transaccion.builder()
                .tipoTransaccion(Transaccion.TipoTransaccion.CONSIGNACION)
                .monto(monto)
                .cuentaOrigen(cuenta)
                .fechaTransaccion(LocalDateTime.now())
                .exitosa(true)
                .build();

        transaccionContext.ejecutar(tx);
        return transaccionRepositoryPort.save(tx); // Aquí se persiste la transacción
    }

    // ---------------------------------------------------------
    // RETIRAR
    // ---------------------------------------------------------
    public Transaccion retirar(Long cuentaId, BigDecimal monto) {
        Cuenta cuenta = cuentaRepositoryPort.findById(cuentaId)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

        Transaccion tx = Transaccion.builder()
                .tipoTransaccion(Transaccion.TipoTransaccion.RETIRO)
                .monto(monto)
                .cuentaOrigen(cuenta)
                .fechaTransaccion(LocalDateTime.now())
                .exitosa(true)
                .build();

        transaccionContext.ejecutar(tx);
        return transaccionRepositoryPort.save(tx);
    }

    // ---------------------------------------------------------
    // TRANSFERIR
    // ---------------------------------------------------------
    public Transaccion transferir(Long cuentaOrigenId, Long cuentaDestinoId, BigDecimal monto) {
        Cuenta origen = cuentaRepositoryPort.findById(cuentaOrigenId)
                .orElseThrow(() -> new RuntimeException("Cuenta origen no encontrada"));

        Cuenta destino = cuentaRepositoryPort.findById(cuentaDestinoId)
                .orElseThrow(() -> new RuntimeException("Cuenta destino no encontrada"));

        Transaccion tx = Transaccion.builder()
                .tipoTransaccion(Transaccion.TipoTransaccion.TRANSFERENCIA)
                .monto(monto)
                .cuentaOrigen(origen)
                .cuentaDestino(destino)
                .fechaTransaccion(LocalDateTime.now())
                .exitosa(true)
                .build();

        transaccionContext.ejecutar(tx);
        return transaccionRepositoryPort.save(tx);
    }

    // ---------------------------------------------------------
    // LISTAR MOVIMIENTOS
    // ---------------------------------------------------------
    public List<Transaccion> listarMovimientos(Long cuentaId) {
        // Usamos el método definido en el port correcto
        return transaccionRepositoryPort.findByCuentaId(cuentaId);
    }
}