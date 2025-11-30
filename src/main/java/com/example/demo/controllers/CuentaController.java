package com.example.demo.controllers;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CrearCuentaRequest;
import com.example.demo.dto.CuentaResponse;
import com.example.demo.port.in.CuentaUseCase;


@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    private final CuentaUseCase cuentaUseCase;

    public CuentaController(CuentaUseCase cuentaUseCase) {
        this.cuentaUseCase = cuentaUseCase;
    }

    @PostMapping
    public ResponseEntity<CuentaResponse> crearCuenta(@RequestBody CrearCuentaRequest request) {
    	CuentaResponse response = cuentaUseCase.createCuenta(request);
    	return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<CuentaResponse>> obtenerCuentasPorCliente(@PathVariable Long clienteId) {
        List<CuentaResponse> response  = cuentaUseCase.getCuentasByClienteId(clienteId);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/saldo")
    public ResponseEntity<Void> actualizarSaldo(
            @PathVariable Long id,
            @RequestParam double monto
    ) {
        cuentaUseCase.actualizarSaldo(id, monto);
        return ResponseEntity.noContent().build();
    }
}