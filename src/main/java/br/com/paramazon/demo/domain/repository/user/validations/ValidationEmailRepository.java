package br.com.paramazon.demo.domain.repository.user.validations;

import br.com.paramazon.demo.domain.model.user.validations.ValidationEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationEmailRepository extends JpaRepository<ValidationEmail, Long> {
}
