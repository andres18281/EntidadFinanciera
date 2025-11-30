package com.example.demo.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransaccionResponse {

    private Long id;
    private String tipoTransaccion;
    private BigDecimal monto;
    private LocalDateTime fechaTransaccion;
    private Boolean exitosa;

    private Long cuentaOrigenId;
    private Long cuentaDestinoId;
}
