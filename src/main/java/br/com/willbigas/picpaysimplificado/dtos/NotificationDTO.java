package br.com.willbigas.picpaysimplificado.dtos;

import lombok.Builder;

@Builder
public record NotificationDTO(String email, String message) {
}
