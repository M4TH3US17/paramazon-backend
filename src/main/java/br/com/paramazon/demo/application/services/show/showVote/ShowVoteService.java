package br.com.paramazon.demo.application.services.show.showVote;

import br.com.paramazon.demo.domain.repository.show.showVote.ShowVoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShowVoteService {

    private final ShowVoteRepository repository;

}
