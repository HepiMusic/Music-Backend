package com.hepi.music_api.vote.service;

import com.hepi.music_api.vote.dto.VoteDTO;
import com.hepi.music_api.vote.model.Vote;

public interface VoteService {
    Vote castVote(VoteDTO voteDTO);
}
