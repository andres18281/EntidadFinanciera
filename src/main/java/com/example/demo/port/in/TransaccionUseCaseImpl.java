package com.example.demo.port.in;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.example.demo.dto.CrearTransaccionRequest;
import com.example.demo.dto.TransaccionResponse;
import com.example.demo.entity.Transaccion;
import com.example.demo.mapper.TransaccionMapper;
import com.example.demo.service.TransaccionService;

@Service
@Transactional
public class TransaccionUseCaseImpl implements TransaccionUseCase {

    private final TransaccionService transaccionService;

    public TransaccionUseCaseImpl(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @Override
    public TransaccionResponse consignar(Long cuentaId, BigDecimal monto) {
        Transaccion tx = transaccionService.consignar(cuentaId, monto);
        return TransaccionMapper.toResponse(tx);
    }

    @Override
    public TransaccionResponse retirar(Long cuentaId, BigDecimal monto) {
        Transaccion tx = transaccionService.retirar(cuentaId, monto);
        return TransaccionMapper.toResponse(tx);
    }

    @Override
    public TransaccionResponse transferir(CrearTransaccionRequest request) {

    	 Transaccion tx = transaccionService.transferir(
    		        request.getCuentaOrigenId(),
    		        request.getCuentaDestinoId(),
    		        request.getMonto()
    		    );
        return TransaccionMapper.toResponse(tx);
    }

    @Override
    public List<TransaccionResponse> listarMovimientos(Long cuentaId) {
    	 return transaccionService
    	            .listarMovimientos(cuentaId)
    	            .stream()
    	            .map(TransaccionMapper::toResponse)
    	            .collect(Collectors.toList());
    }
}
