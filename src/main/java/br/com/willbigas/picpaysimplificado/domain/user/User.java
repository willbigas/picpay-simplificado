package br.com.willbigas.picpaysimplificado.domain.user;

import br.com.willbigas.picpaysimplificado.dtos.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String firstName;

	private String lastName;

	@Column(unique = true)
	private String document;

	@Column(unique = true)
	private String email;

	private String password;

	private BigDecimal balance;

	@Enumerated(EnumType.STRING)
	private UserType userType;

	public User(UserDTO userDTO) {
		this.firstName = userDTO.firstName();
		this.lastName = userDTO.lastName();
		this.document = userDTO.document();
		this.balance = userDTO.balance();
		this.userType = userDTO.userType();
		this.password = userDTO.password();
		this.email = userDTO.email();
	}

	public void substractBalance(BigDecimal value) {
		this.setBalance(this.getBalance().subtract(value));
	}

	public void addBalance(BigDecimal value) {
		this.setBalance(this.getBalance().add(value));
	}
}
