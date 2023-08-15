package br.com.willbigas.picpaysimplificado.services;

import br.com.willbigas.picpaysimplificado.client.AuthorizerClient;
import br.com.willbigas.picpaysimplificado.domain.transaction.Transaction;
import br.com.willbigas.picpaysimplificado.domain.user.User;
import br.com.willbigas.picpaysimplificado.dtos.TransactionDTO;
import br.com.willbigas.picpaysimplificado.repositories.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {

	private final UserService userService;
	private final TransactionRepository transactionRepository;
	private final AuthorizerClient authorizerClient;

	@Transactional
	public void createTransaction(TransactionDTO transactionDTO) throws Exception {
		User sender = this.userService.findUserById(transactionDTO.senderId());
		User receiver = this.userService.findUserById(transactionDTO.receiverId());

		this.userService.validateTransaction(sender, transactionDTO.value());

		boolean isAuthorized = this.authorizerClient.checkTransactionIsAuthorize(sender, transactionDTO.value());

		if (!isAuthorized) {
			throw new Exception("Transação não autorizada");
		}

		Transaction newTransaction = Transaction.builder()
				.amount(transactionDTO.value())
				.sender(sender)
				.receiver(receiver)
				.timestamp(LocalDateTime.now())
				.build();

		sender.substractBalance(transactionDTO.value());
		receiver.addBalance(transactionDTO.value());

		transactionRepository.save(newTransaction);
		userService.saveUser(sender);
		userService.saveUser(receiver);
	}

}
