package com.example.demo.port.in;


import com.example.demo.entity.Transaccion;

public interface TransaccionUseCase {
    Transaccion createTransaccion(Transaccion transaccion);
}
