package com.senaidev.prjMercadoPratico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senaidev.prjMercadoPratico.entities.PedidoUsuario;
import com.senaidev.prjMercadoPratico.enums.StatusPedido;
import com.senaidev.prjMercadoPratico.services.PedidoUsuarioService;

@RestController
@RequestMapping("/webhook-stripe")
public class StripeWebhookController {

    @Autowired
    private PedidoUsuarioService pedidoUsuarioService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping
    public String handleStripeEvent(@RequestBody String payload,
                                    @RequestHeader(name = "Stripe-Signature", required = false) String sigHeader) {
        try {
            JsonNode json = objectMapper.readTree(payload);
            String eventType = json.get("type").asText();
            JsonNode dataObject = json.path("data").path("object");

            System.out.println("Evento recebido do Stripe: " + eventType);

            switch (eventType) {
                case "checkout.session.completed":
                    handleCheckoutSessionCompleted(dataObject);
                    break;

                case "payment_intent.succeeded":
                case "payment_intent.payment_failed":
                    handlePaymentIntentEvent(dataObject);
                    break;

                case "charge.succeeded":
                case "charge.updated":
                    handleChargeEvent(dataObject);
                    break;

                default:
                    System.out.println("Evento não tratado especificamente: " + eventType);
                    System.out.println("Conteúdo do evento: " + dataObject.toString());
            }

        } catch (Exception e) {
            System.err.println("Erro ao processar webhook do Stripe: " + e.getMessage());
            e.printStackTrace();
        }

        return "Evento recebido";
    }

    private void handleCheckoutSessionCompleted(JsonNode session) {
        try {
            String clientReferenceId = session.path("client_reference_id").asText(null);
            String paymentIntentId = session.path("payment_intent").asText(null);

            PedidoUsuario pedido = null;
            if (clientReferenceId != null) {
                pedido = pedidoUsuarioService.buscarPorId(Long.parseLong(clientReferenceId));
            } else if (paymentIntentId != null) {
                pedido = pedidoUsuarioService.buscarPorPaymentIntent(paymentIntentId);
            }

            if (pedido != null) {
                pedidoUsuarioService.atualizarStatus(pedido.getIdPedidoUsuario(), StatusPedido.PAGO);
                System.out.println("Pedido atualizado para PAGO: " + pedido.getIdPedidoUsuario());
            } else {
                System.err.println("Não foi possível identificar o pedido na sessão do Stripe!");
            }

        } catch (Exception e) {
            System.err.println("Erro ao desserializar checkout.session.completed: " + e.getMessage());
        }
    }

    private void handlePaymentIntentEvent(JsonNode paymentIntent) {
        String paymentIntentId = paymentIntent.path("id").asText();
        PedidoUsuario pedido = pedidoUsuarioService.buscarPorPaymentIntent(paymentIntentId);

        if (pedido != null) {
            pedidoUsuarioService.atualizarStatus(pedido.getIdPedidoUsuario(), StatusPedido.PAGO);
            System.out.println("Pedido atualizado para PAGO via payment_intent: " + pedido.getIdPedidoUsuario());
        } else {
            System.err.println("Pedido não encontrado para payment_intent: " + paymentIntentId);
        }
    }

    private void handleChargeEvent(JsonNode charge) {
        String paymentIntentId = charge.path("payment_intent").asText();
        PedidoUsuario pedido = pedidoUsuarioService.buscarPorPaymentIntent(paymentIntentId);

        if (pedido != null) {
            pedidoUsuarioService.atualizarStatus(pedido.getIdPedidoUsuario(), StatusPedido.PAGO);
            System.out.println("Pedido atualizado para PAGO via charge: " + pedido.getIdPedidoUsuario());
        } else {
            System.err.println("Não foi possível identificar o pedido no charge: " + paymentIntentId);
        }
    }
}
