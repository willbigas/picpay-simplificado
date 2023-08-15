package br.com.willbigas.picpaysimplificado.services;

import br.com.willbigas.picpaysimplificado.client.NotificationClient;
import br.com.willbigas.picpaysimplificado.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotificationService {

	private final RestTemplate restTemplate;
	private final NotificationClient notificationClient;

	public void sendNotification(User user , String message) throws Exception {
		String email = user.getEmail();
		notificationClient.notify(email , message);
	}
}
