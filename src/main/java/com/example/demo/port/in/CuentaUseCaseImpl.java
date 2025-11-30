package com.example.demo.port.in;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CrearCuentaRequest;
import com.example.demo.dto.CuentaResponse;
import com.example.demo.service.CuentaService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CuentaUseCaseImpl implements CuentaUseCase {

    private final CuentaService cuentaService; 

    @Override
    public CuentaResponse createCuenta(CrearCuentaRequest request) {
        return cuentaService.createCuenta(request);
    }

    @Override
    public List<CuentaResponse> getCuentasByClienteId(Long clienteId) {
        return cuentaService.getCuentasByClienteId(clienteId);
    }

    @Override
    public void actualizarSaldo(Long cuentaId, double monto) {
        cuentaService.actualizarSaldo(cuentaId, monto);
    }
}