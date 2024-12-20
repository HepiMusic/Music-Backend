package com.hepi.music_api.security.role.service;


import com.hepi.music_api.exception.ResourceNotFoundException;
import com.hepi.music_api.security.permission.model.Permission;
import com.hepi.music_api.security.permission.repository.PermissionRepository;
import com.hepi.music_api.security.role.dto.RoleDTO;
import com.hepi.music_api.security.role.model.Role;
import com.hepi.music_api.security.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * Creates a new role with the specified details and permissions.
     * 
     * @param roleDTO - Data transfer object containing role details and associated permission IDs.
     * @return The created Role object.
     */
    @Transactional
    public Role createRole(RoleDTO roleDTO) {
        // Create new Role instance from DTO
        Role role = new Role();
        role.setRoleName(roleDTO.getRoleName());
        role.setRoleShortDesc(roleDTO.getRoleShortDesc());
        role.setRoleDescription(roleDTO.getRoleDescription());
        role.setRoleStatus(roleDTO.getRoleStatus());

        // Fetch permissions based on provided IDs
        // Handle potential null value for permissionIds

        if (roleDTO.getPermissionIds() != null) {

            Set<Permission> permissions = roleDTO.getPermissionIds().stream()
                    .map(permissionId -> permissionRepository.findById(permissionId)
                            .orElseThrow(() -> new ResourceNotFoundException("Permission", "ID", permissionId)))
                    .collect(Collectors.toSet());
            role.setPermissions(permissions);
        } else {
            role.setPermissions(Collections.emptySet());
        }

        // Save and return the role
        return roleRepository.save(role);
    }

    /**
     * Retrieves a role by its unique role code.
     * 
     * @param roleCode - The unique identifier of the role.
     * @return The Role object if found.
     */
    public Role getRoleById(Integer roleCode) {
        return roleRepository.findById(roleCode)
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    /**
     * Deletes a role by its unique role code.
     * 
     * @param roleCode - The unique identifier of the role to delete.
     */
    public void deleteRole(Integer roleCode) {
        roleRepository.deleteById(roleCode);
    }

    /**
     * Retrieves all roles.
     * 
     * @return A list of all Role objects.
     */
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    /**
     * Updates an existing role with new details and permissions.
     * 
     * @param roleCode - The unique identifier of the role to update.
     * @param roleDTO - Data transfer object containing updated role details and permissions.
     * @return The updated Role object.
     */
    @Transactional
    public Role updateRole(Integer roleCode, RoleDTO roleDTO) {
        // Fetch the existing role by ID
        Role role = getRoleById(roleCode);
        
        // Update role details
        role.setRoleName(roleDTO.getRoleName());
        role.setRoleShortDesc(roleDTO.getRoleShortDesc());
        role.setRoleDescription(roleDTO.getRoleDescription());
        role.setRoleStatus(roleDTO.getRoleStatus());

        // Update permissions

        Set<Permission> permissions = new HashSet<>();
        for (Integer permissionId : roleDTO.getPermissionIds()) {

            permissionRepository.findById(permissionId).ifPresent(permissions::add);
        }
        role.setPermissions(permissions);

        // Save and return the updated role
        return roleRepository.save(role);
    }
}
