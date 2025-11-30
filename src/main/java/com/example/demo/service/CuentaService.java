package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.port.in.CuentaUseCase;
import com.example.demo.port.out.ClienteRepositoryPort;
import com.example.demo.port.out.CuentaRepositoryPort;
import com.example.demo.service.strategy.CuentaContext;
import com.example.demo.dto.CrearCuentaRequest;
import com.example.demo.dto.CuentaResponse;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Cuenta;
import com.example.demo.mapper.CuentaMapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CuentaService implements CuentaUseCase {

	private final CuentaRepositoryPort cuentaRepositoryPort;
    private final ClienteRepositoryPort clienteRepositoryPort;

    public CuentaService(CuentaRepositoryPort cuentaRepositoryPort,
            ClienteRepositoryPort clienteRepositoryPort) {
			this.cuentaRepositoryPort = cuentaRepositoryPort;
			this.clienteRepositoryPort = clienteRepositoryPort;
    }

	    @Override
	    public CuentaResponse createCuenta(CrearCuentaRequest request) {
	        Cliente cliente = clienteRepositoryPort.findById(request.getClienteId())
	                          .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
	        
	        // Generar número de cuenta, mapear y guardar
	        String numeroCuenta = generarNumeroCuenta();
	        Cuenta cuenta = CuentaMapper.toEntity(request, cliente, numeroCuenta);
	        Cuenta cuentaGuardada = cuentaRepositoryPort.save(cuenta);
	        return CuentaMapper.toResponse(cuentaGuardada);
	    }

    @Override
    public List<CuentaResponse> getCuentasByClienteId(Long clienteId) {
        List<Cuenta> cuentas = cuentaRepositoryPort.findByClienteId(clienteId);
        return cuentas.stream().map(CuentaMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public void actualizarSaldo(Long cuentaId, double monto) {
        Cuenta cuenta = cuentaRepositoryPort.findById(cuentaId)
                          .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        cuenta.setSaldo(cuenta.getSaldo().add(BigDecimal.valueOf(monto)));
        cuentaRepositoryPort.save(cuenta);
    }
    
    private String generarNumeroCuenta() {
        // Implementa la lógica para generar un número de cuenta único
        return "1234567890";
    }
}
