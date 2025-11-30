package com.example.demo.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class CrearTransaccionRequest {

    private String tipoTransaccion;   // CONSIGNACION, RETIRO, TRANSFERENCIA
    private BigDecimal monto;

    private Long cuentaOrigenId;
    private Long cuentaDestinoId; // opcional para transferencias
}
