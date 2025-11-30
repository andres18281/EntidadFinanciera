package com.example.demo.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class ClienteResponse {

    private Long id;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombres;
    private String apellidos;
    private String correo;
    private LocalDate fechaNacimiento;

    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;

    private List<Long> cuentasIds; // solo ids para no devolver entidades completas
}
