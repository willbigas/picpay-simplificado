package br.com.willbigas.picpaysimplificado.services;

import br.com.willbigas.picpaysimplificado.domain.user.User;
import br.com.willbigas.picpaysimplificado.domain.user.UserType;
import br.com.willbigas.picpaysimplificado.dtos.UserDTO;
import br.com.willbigas.picpaysimplificado.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public void validateTransaction(User sender, BigDecimal amount) throws Exception {

		if (sender.getUserType() == UserType.MERCHANT) {
			throw new Exception("Usuário do tipo Lojista não esta autorizado a realizar transação");
		}

		if (sender.getBalance().compareTo(amount) < 0) {
			throw new Exception("Saldo insuficiente");
		}
	}

	public User findUserById(Long id) throws Exception {
		return userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
	}

	public User createUser(UserDTO userDTO) {
		User user = new User(userDTO);
		this.saveUser(user);
		return user;
	}

	public void saveUser(User user) {
		this.userRepository.save(user);
	}

	public List<User> getAllUsers() {
		return this.userRepository.findAll();
	}
}
