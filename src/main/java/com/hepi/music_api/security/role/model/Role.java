package com.hepi.music_api.security.role.model;

import com.hepi.music_api.security.permission.model.Permission;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_code")
    private Integer roleCode;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_short_desc")
    private String roleShortDesc;

    @Column(name = "role_description")
    private String roleDescription;

    @Column(name = "role_status")
    private String roleStatus;


    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModified;


    @CreatedBy
    @Column(name = "role_created_by",
            nullable = true,
            updatable = false
    )
    private Integer createdBy;

    @LastModifiedBy
    @Column(name = "role_updated_by", insertable = false)
    private Integer lastModifiedBy;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_code"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;


    @PrePersist
    @PreUpdate
    @PreRemove
    private void init() {
        this.createDate = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();

    }
    @Version
    @Column(name = "VERSION")
    private Long version;


}
