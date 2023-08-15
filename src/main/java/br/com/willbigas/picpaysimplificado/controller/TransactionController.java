package br.com.willbigas.picpaysimplificado.controller;

import br.com.willbigas.picpaysimplificado.domain.transaction.Transaction;
import br.com.willbigas.picpaysimplificado.domain.user.User;
import br.com.willbigas.picpaysimplificado.dtos.TransactionDTO;
import br.com.willbigas.picpaysimplificado.dtos.UserDTO;
import br.com.willbigas.picpaysimplificado.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

	private final TransactionService transactionService;

	@PostMapping
	public ResponseEntity<Transaction> createUser(@RequestBody TransactionDTO userDTO) throws Exception {
		Transaction transaction = transactionService.createTransaction(userDTO);
		return new ResponseEntity<>(transaction, HttpStatus.CREATED);
	}

}
