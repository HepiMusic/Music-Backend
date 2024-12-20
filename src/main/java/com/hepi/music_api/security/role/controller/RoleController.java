package com.hepi.music_api.security.role.controller;

import com.hepi.music_api.security.role.dto.RoleDTO;
import com.hepi.music_api.security.role.model.Role;
import com.hepi.music_api.security.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * Endpoint to create a new role.
     * 
     * @param roleDTO - Data transfer object containing role details and associated permission IDs.
     * @return The created Role object.
     */
    @PostMapping
    public Role createRole(@RequestBody RoleDTO roleDTO) {
        return roleService.createRole(roleDTO);
    }

    /**
     * Endpoint to retrieve a role by its unique role code.
     * 
     * @param roleCode - The unique identifier of the role.
     * @return The Role object if found.
     */
    @GetMapping("/{roleCode}")
    public Role getRole(@PathVariable Integer roleCode) {
        return roleService.getRoleById(roleCode);
    }

    /**
     * Endpoint to delete a role by its unique role code.
     * 
     * @param roleCode - The unique identifier of the role to delete.
     */
    @DeleteMapping("/{roleCode}")
    public void deleteRole(@PathVariable Integer roleCode) {
        roleService.deleteRole(roleCode);
    }

    /**
     * Endpoint to retrieve all roles.
     * 
     * @return A list of all Role objects.
     */
    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    /**
     * Endpoint to update an existing role with new details and permissions.
     * 
     * @param roleCode - The unique identifier of the role to update.
     * @param roleDTO - Data transfer object containing updated role details and permissions.
     * @return The updated Role object.
     */
    @PutMapping("/{roleCode}")
    public Role updateRole(@PathVariable Integer roleCode, @RequestBody RoleDTO roleDTO) {
        return roleService.updateRole(roleCode, roleDTO);
    }
}
