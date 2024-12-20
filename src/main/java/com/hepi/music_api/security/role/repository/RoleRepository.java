package com.hepi.music_api.security.role.repository;


import com.hepi.music_api.security.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRoleName(String roleName);


    Optional<Role> findByRoleCode(Integer roleCode);
}

