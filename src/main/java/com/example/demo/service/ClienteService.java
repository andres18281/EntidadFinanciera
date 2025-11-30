package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.port.in.ClienteUseCase;
import com.example.demo.port.out.ClienteRepositoryPort;

import lombok.RequiredArgsConstructor;

import com.example.demo.dto.CrearClienteRequest;
import com.example.demo.entity.Cliente;
import com.example.demo.mapper.ClienteMapper;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepositoryPort clienteRepositoryPort;

    // Crear un nuevo cliente
    public Cliente createCliente(CrearClienteRequest request) {
        if (clienteRepositoryPort.existsByNumeroIdentificacion(request.getNumeroIdentificacion())) {
            throw new RuntimeException("Cliente con esa identificación ya existe");
        }

        Cliente cliente = ClienteMapper.toEntity(request);
        cliente.setFechaCreacion(LocalDate.now());
        return clienteRepositoryPort.save(cliente);
    }

    // Obtener cliente por ID
    public Cliente getClienteById(Long id) {
        return clienteRepositoryPort.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
    }

    // Actualizar un cliente existente
    public Cliente actualizarCliente(Long id, CrearClienteRequest request) {
        Cliente cliente = getClienteById(id);
        ClienteMapper.updateEntity(cliente, request); // actualiza solo campos editables
        cliente.setFechaModificacion(LocalDate.now());
        return clienteRepositoryPort.save(cliente);
    }

    // Eliminar un cliente
    public void deleteCliente(Long id) {
        Cliente cliente = getClienteById(id);
        clienteRepositoryPort.delete(cliente);
    }

    // Listar todos los clientes
    public List<Cliente> listarClientes() {
        return clienteRepositoryPort.findAll();
    }
}
