package com.hepi.music_api.vote.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteDTO {
    private Long id;
    private String voteType;
    private Long songId;
    private Long userId;
}
