package com.hepi.music_api.security.user.repository;


import com.hepi.music_api.security.user.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  Page<User> findAll(Pageable pageable);
  Optional<User> findById(Long  userCode);

  @Transactional
  @Modifying
  @Query("update User u set u.password = ?2 where u.email = ?1")
  void updatePassword(String email, String password);

}
