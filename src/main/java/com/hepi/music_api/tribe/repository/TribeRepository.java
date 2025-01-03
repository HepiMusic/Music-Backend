package com.hepi.music_api.tribe.repository;

import com.hepi.music_api.tribe.Tribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TribeRepository extends JpaRepository<Tribe, Long> {
    Optional<Tribe> findByTribeId(Long tribeId);
}
