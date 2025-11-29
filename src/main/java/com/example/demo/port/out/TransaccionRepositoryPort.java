package com.example.demo.port.out;

import com.example.demo.entity.Transaccion;
import java.util.List;

public interface TransaccionRepositoryPort {
	Transaccion save(Transaccion transaccion);
    List<Transaccion> findByCuentaId(Long cuentaId);
}
