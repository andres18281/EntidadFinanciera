package com.example.demo.port.in;

import com.example.demo.entity.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteUseCase {
	
    Cliente createCliente(Cliente cliente);
    List<Cliente> getAllClientes();
    Optional<Cliente> getClienteById(Long id);
    void deleteCliente(Long id);
    
}