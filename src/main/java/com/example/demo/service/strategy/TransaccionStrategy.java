package com.example.demo.service.strategy;

import com.example.demo.entity.Transaccion;

public interface TransaccionStrategy {
	Transaccion.TipoTransaccion getTipo();  // <-- cada estrategia dice qué tipo maneja
    void ejecutar(Transaccion transaccion);
}
