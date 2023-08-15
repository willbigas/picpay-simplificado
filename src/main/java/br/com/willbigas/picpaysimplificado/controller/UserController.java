package br.com.willbigas.picpaysimplificado.controller;

import br.com.willbigas.picpaysimplificado.domain.user.User;
import br.com.willbigas.picpaysimplificado.dtos.UserDTO;
import br.com.willbigas.picpaysimplificado.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
		User newUser = userService.createUser(userDTO);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}
