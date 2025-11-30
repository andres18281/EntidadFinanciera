package com.example.demo.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CrearCuentaRequest {

    private Long clienteId;
    private String tipoCuenta;   // AHORROS, CORRIENTE
    private Boolean exentaGMF;
    private BigDecimal saldoInicial;
}
