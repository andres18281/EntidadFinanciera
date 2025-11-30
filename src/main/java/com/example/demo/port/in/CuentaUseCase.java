package com.example.demo.port.in;


import java.util.List;

import com.example.demo.dto.CrearCuentaRequest;
import com.example.demo.dto.CuentaResponse;


public interface CuentaUseCase {
	 CuentaResponse createCuenta(CrearCuentaRequest request);
	 List<CuentaResponse> getCuentasByClienteId(Long clienteId);
	 void actualizarSaldo(Long cuentaId, double monto);
}