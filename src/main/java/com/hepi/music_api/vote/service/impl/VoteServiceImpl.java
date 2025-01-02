package com.hepi.music_api.vote.service.impl;

import com.hepi.music_api.exception.ResourceNotFoundException;
import com.hepi.music_api.security.user.model.User;
import com.hepi.music_api.security.user.repository.UserRepository;
import com.hepi.music_api.songs.model.Song;
import com.hepi.music_api.songs.repository.SongRepository;
import com.hepi.music_api.vote.dto.VoteDTO;
import com.hepi.music_api.vote.model.Vote;
import com.hepi.music_api.vote.repository.VoteRepository;
import com.hepi.music_api.vote.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final SongRepository songRepository;
    private final UserRepository userRepository;

    @Override
    public Vote castVote(VoteDTO voteDTO) {
        Song song = songRepository.findBySongId(voteDTO.getSongId())
                .orElseThrow(() -> new IllegalArgumentException("Song not found with ID: " + voteDTO.getSongId()));
        User user = userRepository.findById(voteDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + voteDTO.getUserId()));

        Optional<Vote> existingVote = voteRepository.findBySongIdAndUserId(song.getSongId(), user.getId());
        if (existingVote.isPresent()) {
            existingVote.get().setVoteType(voteDTO.getVoteType());
            return voteRepository.save(existingVote.get());
        }

        Vote vote = new Vote();
        vote.setVoteType(voteDTO.getVoteType());
        vote.setSong(song);
        vote.setUser(user);

        return voteRepository.save(vote);
    }
}
