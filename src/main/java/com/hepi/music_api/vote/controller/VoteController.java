package com.hepi.music_api.vote.controller;

import com.hepi.music_api.vote.dto.VoteDTO;
import com.hepi.music_api.vote.model.Vote;
import com.hepi.music_api.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votes")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    public ResponseEntity<Vote> castVote(@RequestBody VoteDTO voteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(voteService.castVote(voteDTO));
    }
}
