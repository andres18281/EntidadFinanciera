package com.example.demo.entity;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "cuentas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_cuenta", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoCuenta tipoCuenta; // AHORROS o CORRIENTE

    @Column(name = "numero_cuenta", nullable = false, unique = true, length = 10)
    private String numeroCuenta;

    @Column(name = "estado", nullable = false)
    @Enumerated(EnumType.STRING)
    private EstadoCuenta estado; // ACTIVA, INACTIVA, CANCELADA

    @Column(nullable = false)
    private BigDecimal saldo;

    @Column(name = "exenta_gmf", nullable = false)
    private Boolean exentaGMF;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // Enums para tipo y estado de cuenta
    public enum TipoCuenta {
        AHORROS, CORRIENTE
    }

    public enum EstadoCuenta {
        ACTIVA, INACTIVA, CANCELADA
    }
}
