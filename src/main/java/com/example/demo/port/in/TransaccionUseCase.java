package com.example.demo.port.in;


import java.math.BigDecimal;
import java.util.List;

import com.example.demo.dto.CrearTransaccionRequest;
import com.example.demo.dto.TransaccionResponse;


public interface TransaccionUseCase {
    TransaccionResponse consignar(Long cuentaId, BigDecimal monto);
    TransaccionResponse retirar(Long cuentaId, BigDecimal monto);
    TransaccionResponse transferir(CrearTransaccionRequest request);
    List<TransaccionResponse> listarMovimientos(Long cuentaId);
    
}