package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Transaccion;
import com.example.demo.port.out.TransaccionRepositoryPort;



@Repository
public interface TransaccionRepositoryAdapter extends JpaRepository<Transaccion, Long>, TransaccionRepositoryPort {

    
  
}