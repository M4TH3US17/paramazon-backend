package br.com.paramazon.demo.domain.repository.user;


import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByStatus(Status status);
    Optional<User> findByIdUserAndStatus(Long id, Status status);
    @Query("SELECT u FROM User u JOIN u.role r WHERE u.idUser IN :memberIds AND u.status = :status AND r.name = 'ROLE_SINGER'")
    Set<User> findAllByIdUserInAndStatusAndRole(@Param("memberIds") Set<Long> memberIds, @Param("status") Status status);
}
