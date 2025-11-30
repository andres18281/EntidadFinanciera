package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CrearClienteRequest {
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombres;
    private String apellidos;
    private String correo;
    private LocalDate fechaNacimiento;
}
