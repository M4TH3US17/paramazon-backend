package br.com.paramazon.demo.domain.repository.show.showVote;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.showVote.ShowVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShowVoteRepository extends JpaRepository<ShowVote, Long> {
    List<ShowVote> findAllByStatus(Status status);

    Optional<ShowVote> findByIdShowVoteAndStatus(Long idShowVote, Status status);
}
