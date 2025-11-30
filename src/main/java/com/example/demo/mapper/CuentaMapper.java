package com.example.demo.mapper;

import com.example.demo.dto.CrearCuentaRequest;
import com.example.demo.dto.CuentaResponse;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Cuenta;

import java.time.LocalDateTime;

public class CuentaMapper {

   
     
    public static Cuenta toEntity(CrearCuentaRequest dto) {
        return Cuenta.builder()
                .tipoCuenta(Cuenta.TipoCuenta.valueOf(dto.getTipoCuenta()))
                .saldo(dto.getSaldoInicial())
                .exentaGMF(dto.getExentaGMF())
                .build();
    }

    
    public static Cuenta toEntity(CrearCuentaRequest dto, Cliente cliente, String numeroCuentaGenerado) {
        return Cuenta.builder()
                .tipoCuenta(Cuenta.TipoCuenta.valueOf(dto.getTipoCuenta()))
                .numeroCuenta(numeroCuentaGenerado)
                .estado(Cuenta.EstadoCuenta.ACTIVA)
                .saldo(dto.getSaldoInicial())
                .exentaGMF(dto.getExentaGMF())
                .fechaCreacion(LocalDateTime.now())
                .cliente(cliente)
                .build();
    }

    
    public static CuentaResponse toResponse(Cuenta entity) {
        CuentaResponse dto = new CuentaResponse();

        dto.setId(entity.getId());
        dto.setTipoCuenta(entity.getTipoCuenta().name());
        dto.setNumeroCuenta(entity.getNumeroCuenta());
        dto.setEstado(entity.getEstado().name());
        dto.setSaldo(entity.getSaldo());
        dto.setExentaGMF(entity.getExentaGMF());
        dto.setFechaCreacion(entity.getFechaCreacion());
        dto.setFechaModificacion(entity.getFechaModificacion());
        dto.setClienteId(
            entity.getCliente() != null ? entity.getCliente().getId() : null
        );

        return dto;
    }
}
