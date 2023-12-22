package br.com.paramazon.demo.domain.repository.user.role;

import br.com.paramazon.demo.domain.model.user.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
