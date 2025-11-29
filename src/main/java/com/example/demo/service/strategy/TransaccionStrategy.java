package com.example.demo.service.strategy;

import com.example.demo.entity.Transaccion;

public interface TransaccionStrategy {
    void ejecutar(Transaccion transaccion);
}
