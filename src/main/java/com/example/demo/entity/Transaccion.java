
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_transaccion", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoTransaccion tipoTransaccion; // CONSIGNACION, RETIRO, TRANSFERENCIA

    @Column(nullable = false)
    private BigDecimal monto;

    @Column(name = "fecha_transaccion", nullable = false)
    private LocalDateTime fechaTransaccion;

    // Cuenta origen de la transacción
    @ManyToOne
    @JoinColumn(name = "cuenta_origen_id", nullable = true)
    private Cuenta cuentaOrigen;

    // Cuenta destino de la transacción (solo para transferencias)
    @ManyToOne
    @JoinColumn(name = "cuenta_destino_id", nullable = true)
    private Cuenta cuentaDestino;

    // Estado de la transacción (opcional, si quieres controlar errores)
    @Column(name = "estado", nullable = false)
    private Boolean exitosa;

    // Enum para tipo de transacción
    public enum TipoTransaccion {
        CONSIGNACION, RETIRO, TRANSFERENCIA
    }
}
