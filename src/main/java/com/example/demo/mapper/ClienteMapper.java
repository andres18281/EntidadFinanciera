package com.example.demo.mapper;

import com.example.demo.dto.CrearClienteRequest;
import com.example.demo.dto.ClienteResponse;
import com.example.demo.entity.Cliente;

import java.util.stream.Collectors;

public class ClienteMapper {

    public static Cliente toEntity(CrearClienteRequest dto) {
        Cliente cliente = new Cliente();
        cliente.setTipoIdentificacion(dto.getTipoIdentificacion());
        cliente.setNumeroIdentificacion(dto.getNumeroIdentificacion());
        cliente.setNombres(dto.getNombres());
        cliente.setApellidos(dto.getApellidos());
        cliente.setCorreo(dto.getCorreo());
        cliente.setFechaNacimiento(dto.getFechaNacimiento());
        return cliente;
    }

    public static ClienteResponse toResponse(Cliente entity) {
        ClienteResponse dto = new ClienteResponse();
        dto.setId(entity.getId());
        dto.setTipoIdentificacion(entity.getTipoIdentificacion());
        dto.setNumeroIdentificacion(entity.getNumeroIdentificacion());
        dto.setNombres(entity.getNombres());
        dto.setApellidos(entity.getApellidos());
        dto.setCorreo(entity.getCorreo());
        dto.setFechaNacimiento(entity.getFechaNacimiento());
        dto.setFechaCreacion(entity.getFechaCreacion());
        dto.setFechaModificacion(entity.getFechaModificacion());

        if (entity.getCuentas() != null) {
            dto.setCuentasIds(
                    entity.getCuentas().stream()
                            .map(c -> c.getId())
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }
    

    public static void updateEntity(Cliente cliente, CrearClienteRequest dto) {
        cliente.setTipoIdentificacion(dto.getTipoIdentificacion());
        cliente.setNumeroIdentificacion(dto.getNumeroIdentificacion());
        cliente.setNombres(dto.getNombres());
        cliente.setApellidos(dto.getApellidos());
        cliente.setCorreo(dto.getCorreo());
        cliente.setFechaNacimiento(dto.getFechaNacimiento());
       
    }
}
