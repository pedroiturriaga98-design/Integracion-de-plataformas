package com.pcfactory.ecommerce.controller;

import com.pcfactory.ecommerce.dto.WebpayCreateResponse;
import com.pcfactory.ecommerce.model.Transaccion;
import com.pcfactory.ecommerce.repository.TransaccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final TransaccionRepository transaccionRepository;

    // 1. SERVICIO GET: Crea la transacción, simula la llamada a Webpay y retorna Token + URL
    @GetMapping("/crear")
    public WebpayCreateResponse crearPago(@RequestParam Double monto) {
        String buyOrder = "O-" + UUID.randomUUID().toString().substring(0, 8);
        String sessionId = "S-" + UUID.randomUUID().toString().substring(0, 8);

        String mockToken = "01ab" + UUID.randomUUID().toString().replace("-", "") + UUID.randomUUID().toString().replace("-", "");
        String mockUrl = "https://webpay3g.transbank.cl/webpayserver/initTransaction";

        // PERSISTENCIA: Registra en la tabla con el estado inicial solicitado: CREADO
        Transaccion tx = new Transaccion();
        tx.setToken(mockToken);
        tx.setMonto(monto);
        tx.setBuyOrder(buyOrder);
        tx.setSessionId(sessionId);
        tx.setEstado("CREADO");
        tx.setFechaCreacion(LocalDateTime.now());
        transaccionRepository.save(tx);

        WebpayCreateResponse response = new WebpayCreateResponse();
        response.setToken(mockToken);
        response.setUrl(mockUrl);

        return response;
    }

    // 2. ENDPOINT POST: Recibe el status desde Webpay y cambia el estado a PAGADO
    @PostMapping("/confirmar")
    public ResponseEntity<String> confirmarPago(@RequestParam("token_ws") String token) {
        return transaccionRepository.findAll().stream()
                .filter(tx -> token.equals(tx.getToken()))
                .findFirst()
                .map(tx -> {
                    tx.setEstado("PAGADO");
                    transaccionRepository.save(tx);
                    return ResponseEntity.ok("Estado de transaccion actualizado a: PAGADO");
                })
                .orElse(ResponseEntity.status(404).body("Token no encontrado"));
    }
}