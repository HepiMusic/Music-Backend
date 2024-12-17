package com.hepi.music_api.security.role.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class RoleDTO {
    private String roleName;
    private String roleShortDesc;
    private String roleDescription;
    private String roleStatus;
    private Set<Integer> permissionIds = new HashSet<>();
}
