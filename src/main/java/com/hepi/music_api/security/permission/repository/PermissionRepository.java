package com.hepi.music_api.security.permission.repository;

import com.hepi.music_api.security.permission.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    Optional<Permission> findByPermissionName(String permissionName);
}
