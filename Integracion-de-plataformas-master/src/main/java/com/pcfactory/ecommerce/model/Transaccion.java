package com.pcfactory.ecommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacciones")
@Data
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private Double monto;
    private String buyOrder;
    private String sessionId;
    private String estado; // CREADO, PAGADO, RECHAZADO
    private LocalDateTime fechaCreacion;
}