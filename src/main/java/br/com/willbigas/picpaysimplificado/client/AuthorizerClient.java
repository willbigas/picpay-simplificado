package br.com.willbigas.picpaysimplificado.client;

import br.com.willbigas.picpaysimplificado.domain.user.User;
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
public class AuthorizerClient {

	private final RestTemplate restTemplate;

	@Value("${microsvc.authorizer.url}")
	private String authorizerClientURL;

	public Boolean checkTransactionIsAuthorize(User sender , BigDecimal value) {
		var authorizationResponse = restTemplate.getForEntity(authorizerClientURL, Map.class);

		if (authorizationResponse.getStatusCode() == HttpStatus.OK && authorizationResponse.getBody().get("message").equals("Autorizado")) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
