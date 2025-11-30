package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Cliente;
import com.example.demo.port.out.ClienteRepositoryPort;



@Repository
public interface ClienteRepositoryAdapter extends JpaRepository<Cliente, Long>, ClienteRepositoryPort {

	boolean existsByNumeroIdentificacion(String numeroIdentificacion);
}