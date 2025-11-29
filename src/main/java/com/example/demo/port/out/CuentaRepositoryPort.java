package com.example.demo.port.out;
import com.example.demo.entity.Cuenta;
import java.util.List;
import java.util.Optional;

public interface CuentaRepositoryPort {
	Cuenta save(Cuenta cuenta);
    List<Cuenta> findByClienteId(Long clienteId);
    Optional<Cuenta> findById(Long id);
}
