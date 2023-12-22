package br.com.paramazon.demo.domain.repository.user;

import br.com.paramazon.demo.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
