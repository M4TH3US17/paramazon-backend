package br.com.paramazon.demo.domain.repository.show.showVote.presentationVote;

import br.com.paramazon.demo.domain.model.show.showVote.presentationVote.PresentationVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentationVoteRepository extends JpaRepository<PresentationVote, Long> {
}
