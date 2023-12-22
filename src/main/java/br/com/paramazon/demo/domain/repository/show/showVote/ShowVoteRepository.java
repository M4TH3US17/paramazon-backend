package br.com.paramazon.demo.domain.repository.show.showVote;

import br.com.paramazon.demo.domain.model.show.showVote.ShowVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowVoteRepository extends JpaRepository<ShowVote, Long> {
}
