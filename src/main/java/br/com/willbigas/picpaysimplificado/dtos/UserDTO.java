package br.com.willbigas.picpaysimplificado.dtos;

import br.com.willbigas.picpaysimplificado.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstName , String lastName , String document, BigDecimal balance, String email, String password, UserType userType) {
}
