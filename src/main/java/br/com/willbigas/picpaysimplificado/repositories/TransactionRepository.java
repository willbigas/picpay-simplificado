package br.com.willbigas.picpaysimplificado.repositories;

import br.com.willbigas.picpaysimplificado.domain.transaction.Transaction;
import br.com.willbigas.picpaysimplificado.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
