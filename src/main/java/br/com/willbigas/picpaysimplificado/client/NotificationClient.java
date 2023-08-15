package br.com.willbigas.picpaysimplificado.client;

import br.com.willbigas.picpaysimplificado.domain.user.User;
import br.com.willbigas.picpaysimplificado.dtos.NotificationDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Map;

@Component
@Getter
@RequiredArgsConstructor
public class NotificationClient {

	private final RestTemplate restTemplate;

	@Value("${microsvc.notification.url}")
	private String notificationURL;

	public void notify(String email, String message) throws Exception {
		NotificationDTO notificationDTO = NotificationDTO.builder()
				.email(email)
				.message(message)
				.build();

		var notificationResponse = restTemplate.postForEntity(notificationURL, notificationDTO, String.class);

		if (notificationResponse.getStatusCode() != HttpStatus.OK) {
			System.out.println("Erro ao enviar notificação");
			throw new Exception("Serviço de notificação esta fora do ar");
		}
	}
}
