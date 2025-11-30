package com.example.demo.port.in;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ClienteResponse;
import com.example.demo.dto.CrearClienteRequest;
import com.example.demo.entity.Cliente;
import com.example.demo.mapper.ClienteMapper;

import com.example.demo.service.ClienteService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ClienteUseCaseImpl implements ClienteUseCase {

    private final ClienteService clienteService;

    @Override
    public ClienteResponse createCliente(CrearClienteRequest request) {
        Cliente cliente = clienteService.createCliente(request);
        return ClienteMapper.toResponse(cliente);
    }

    @Override
    public ClienteResponse getClienteById(Long id) {
        Cliente cliente = clienteService.getClienteById(id);
        return ClienteMapper.toResponse(cliente);
    }

    @Override
    public ClienteResponse actualizarCliente(Long id, CrearClienteRequest request) {
        Cliente cliente = clienteService.actualizarCliente(id, request);
        return ClienteMapper.toResponse(cliente);
    }

    @Override
    public void deleteCliente(Long id) {
        clienteService.deleteCliente(id);
    }

    @Override
    public List<ClienteResponse> listarClientes() {
        return clienteService.listarClientes()
                             .stream()
                             .map(ClienteMapper::toResponse)
                             .collect(Collectors.toList());
    }
}
