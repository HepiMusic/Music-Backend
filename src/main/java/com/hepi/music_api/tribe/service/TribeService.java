package com.hepi.music_api.tribe.service;

import com.hepi.music_api.artist.model.Artist;
import com.hepi.music_api.tribe.Tribe;
import com.hepi.music_api.tribe.dto.TribeDTO;

import java.util.List;

public interface TribeService {

    Tribe createTribe(TribeDTO tribeDTO);

    Tribe updateTribe(Long tribeId, TribeDTO tribeDTO);

    void deleteTribe(Long tribeId);

    Tribe getTribeById(Long tribeId);

    List<Tribe> getAllTribes();

    List<Artist> getArtistsByTribe(Long tribeId);
}
