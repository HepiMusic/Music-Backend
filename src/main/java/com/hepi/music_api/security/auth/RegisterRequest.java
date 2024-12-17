package com.hepi.music_api.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
  private String userFirstname;
  private String userLastname;
  private String userEmail;
  private String userPassword;
  private String userCountryCode;
  private String userPhone;
  private Integer userIdNumber;
  private Integer userHudumaNo;
  private String userUsername;
  private Integer roleId; // ID of the associated role
}
