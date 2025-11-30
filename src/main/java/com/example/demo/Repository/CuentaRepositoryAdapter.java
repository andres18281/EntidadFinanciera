package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Cuenta;
import com.example.demo.port.out.CuentaRepositoryPort;



@Repository
public interface CuentaRepositoryAdapter extends JpaRepository<Cuenta, Long>, CuentaRepositoryPort {

    
}