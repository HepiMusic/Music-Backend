package com.hepi.music_api.security.user.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hepi.music_api.country.Country;
import com.hepi.music_api.security.role.model.Role;
import com.hepi.music_api.security.token.Token;
import com.hepi.music_api.security.user.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USER_CODE")
  private Long id;

  @Column(name = "USER_FIRSTNAME")
  private String firstname;

  @Column(name = "USER_LASTNAME")
  private String lastname;

  @Column(name = "USER_EMAIL",  unique = true)
  @NotBlank(message = "The email field can't be blank")
  @Email(message = "Please enter email in proper format!")
  private String email;

  @Column(name = "USER_PASSWORD")
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(name = "USER_STATUS")
  private UserStatus userStatus;

  @Column(name = "USER_PHONE_NUMBER")
  private String userPhoneNumber;

  @Column(name = "USER_HASHED_MSISDN")
  private String userHashedMsisdn;

  @Column(name = "USER_ID_NUMBER")
  private Integer userIdNumber;

  @ManyToOne
  @JoinColumn(name = "country_id", nullable = true)
  private Country country;
  @OneToMany(mappedBy = "user" ,orphanRemoval = true)
  @JsonIgnore
  private List<Token> tokens;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "role_code", nullable = false)
  private Role role;

  @OneToOne(mappedBy = "user")
  private ForgotPassword forgotPassword;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Comment> comments = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Vote> votes = new ArrayList<>();



  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> authorities = new HashSet<>();

    // Add the role itself as an authority (e.g., "ROLE_ADMIN")
    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));

    // Optionally add permissions, if any
    if (role.getPermissions() != null) {
      authorities.addAll(
              role.getPermissions().stream()
                      .map(permission -> new SimpleGrantedAuthority(permission.getPermissionName()))
                      .collect(Collectors.toSet())
      );
    }

    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
