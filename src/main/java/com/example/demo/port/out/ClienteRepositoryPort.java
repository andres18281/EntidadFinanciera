package com.example.demo.port.out;

import com.example.demo.entity.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteRepositoryPort {
    Cliente save(Cliente cliente);
    List<Cliente> findAll();
    Optional<Cliente> findById(Long id);
    void delete(Cliente cliente);
    boolean existsByNumeroIdentificacion(String numeroIdentificacion);
}