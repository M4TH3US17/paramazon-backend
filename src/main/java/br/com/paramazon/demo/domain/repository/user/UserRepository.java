package br.com.paramazon.demo.domain.repository.user;


import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByStatus(Status status);
    Optional<User> findByIdUserAndStatus(Long id, Status status);
}
