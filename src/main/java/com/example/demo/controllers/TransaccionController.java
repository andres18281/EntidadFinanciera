package com.example.demo.controllers;
import java.math.BigDecimal;
import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CrearTransaccionRequest;
import com.example.demo.dto.TransaccionResponse;

import com.example.demo.mapper.TransaccionMapper;
import com.example.demo.port.in.TransaccionUseCase;


import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transacciones")
@RequiredArgsConstructor
public class TransaccionController {

    private final TransaccionUseCase useCase;
    private final TransaccionMapper mapper;

    @PostMapping("/consignar")
    public ResponseEntity<TransaccionResponse> consignar(
            @RequestParam Long cuentaId,
            @RequestParam BigDecimal monto) {

        TransaccionResponse response = useCase.consignar(cuentaId, monto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/retirar")
    public ResponseEntity<TransaccionResponse> retirar(
            @RequestParam Long cuentaId,
            @RequestParam BigDecimal monto) {

        TransaccionResponse response = useCase.retirar(cuentaId, monto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/transferir")
    public ResponseEntity<TransaccionResponse> transferir(
            @RequestBody CrearTransaccionRequest request) {

        TransaccionResponse response = useCase.transferir(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{cuentaId}/movimientos")
    public ResponseEntity<List<TransaccionResponse>> listar(
            @PathVariable Long cuentaId) {


        List<TransaccionResponse> movimientos = useCase.listarMovimientos(cuentaId);
        return ResponseEntity.ok(movimientos);
    }
}