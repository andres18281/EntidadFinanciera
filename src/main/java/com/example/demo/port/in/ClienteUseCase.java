package com.example.demo.port.in;

import com.example.demo.dto.ClienteResponse;
import com.example.demo.dto.CrearClienteRequest;

import java.util.List;
	
public interface ClienteUseCase {
	
	 ClienteResponse createCliente(CrearClienteRequest request);

	 ClienteResponse getClienteById(Long id);

	 ClienteResponse actualizarCliente(Long id, CrearClienteRequest request);

	 void deleteCliente(Long id);

	 List<ClienteResponse> listarClientes();
    
}