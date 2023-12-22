package br.com.paramazon.demo.application.services.show.showVote.presentationVote;

import br.com.paramazon.demo.domain.repository.show.showVote.presentationVote.PresentationVoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PresentationVoteService {

    private final PresentationVoteRepository repository;


}
