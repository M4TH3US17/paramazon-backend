package br.com.paramazon.demo.domain.repository.show.showVote.presentationVote;

import br.com.paramazon.demo.domain.enums.Status;
import br.com.paramazon.demo.domain.model.show.showVote.presentationVote.PresentationVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PresentationVoteRepository extends JpaRepository<PresentationVote, Long> {
    List<PresentationVote> findAllByStatus(Status status);

    Optional<PresentationVote> findByIdPresentationVoteAndStatus(Long idPresentationVote, Status status);
}
