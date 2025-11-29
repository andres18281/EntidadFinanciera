package com.example.demo.controllers;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    private final TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @PostMapping("/consignacion")
    public ResponseEntity<Void> consignar(@RequestBody TransaccionRequest request) {
        transaccionService.consignar(request.getCuentaDestinoId(), request.getMonto());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/retiro")
    public ResponseEntity<Void> retirar(@RequestBody TransaccionRequest request) {
        transaccionService.retirar(request.getCuentaOrigenId(), request.getMonto());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transferencia")
    public ResponseEntity<Void> transferir(@RequestBody TransferenciaRequest request) {
        transaccionService.transferir(request.getCuentaOrigenId(), request.getCuentaDestinoId(), request.getMonto());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cuenta/{cuentaId}")
    public ResponseEntity<List<Transaccion>> listarMovimientos(@PathVariable Long cuentaId) {
        return ResponseEntity.ok(transaccionService.listarMovimientos(cuentaId));
    }
}
