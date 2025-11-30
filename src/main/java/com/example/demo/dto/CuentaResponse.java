package com.example.demo.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CuentaResponse {

    private Long id;
    private String tipoCuenta;
    private String numeroCuenta;
    private String estado;
    private BigDecimal saldo;
    private Boolean exentaGMF;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaModificacion;

    private Long clienteId;
}
