package br.com.loja.mudi.repository;

import br.com.loja.mudi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
