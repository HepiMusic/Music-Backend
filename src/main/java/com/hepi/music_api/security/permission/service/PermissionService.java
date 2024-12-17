package com.hepi.music_api.security.permission.service;

import com.hepi.music_api.security.permission.dto.PermissionDTO;
import com.hepi.music_api.security.permission.model.Permission;
import com.hepi.music_api.security.permission.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission createPermission(PermissionDTO permissionDTO) {
        Permission permission = new Permission();
        permission.setPermissionName(permissionDTO.getPermissionName());
        permission.setPermissionDescription(permissionDTO.getPermissionDescription());
        return permissionRepository.save(permission);
    }

    public List<Permission> getAllPermissions() {
        return permissionRepository.findAll();
    }

    public Optional<Permission> getPermissionById(Integer id) {
        return permissionRepository.findById(id);
    }

    public Optional<Permission> getPermissionByName(String permissionName) {
        return permissionRepository.findByPermissionName(permissionName);
    }

    public Permission updatePermission(Integer id, PermissionDTO permissionDTO) {
        Permission permission = permissionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Permission not found"));

        permission.setPermissionName(permissionDTO.getPermissionName());
        permission.setPermissionDescription(permissionDTO.getPermissionDescription());
        
        return permissionRepository.save(permission);
    }

    public void deletePermission(Integer id) {
        if (permissionRepository.existsById(id)) {
            permissionRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Permission not found");
        }
    }
}
