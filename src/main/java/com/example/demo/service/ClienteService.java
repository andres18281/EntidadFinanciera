package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.port.in.ClienteUseCase;
import com.example.demo.port.out.ClienteRepositoryPort;
import com.example.demo.entity.Cliente;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService implements ClienteUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;

    public ClienteService(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }

    @Override
    public Cliente createCliente(Cliente cliente) {
        return clienteRepositoryPort.save(cliente);
    }

    @Override
    public List<Cliente> getAllClientes() {
        return clienteRepositoryPort.findAll();
    }

    @Override
    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepositoryPort.findById(id);
    }

    @Override
    public void deleteCliente(Long id) {
        clienteRepositoryPort.findById(id).ifPresent(clienteRepositoryPort::delete);
    }
}
