package com.hepi.music_api.security.auth.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hepi.music_api.security.auth.AuthenticationRequest;
import com.hepi.music_api.security.auth.AuthenticationResponse;
import com.hepi.music_api.security.auth.AuthenticationService;
import com.hepi.music_api.security.auth.RegisterRequest;
import com.hepi.music_api.security.config.JwtService;
import com.hepi.music_api.security.role.model.Role;
import com.hepi.music_api.security.role.repository.RoleRepository;
import com.hepi.music_api.security.token.Token;
import com.hepi.music_api.security.token.TokenRepository;
import com.hepi.music_api.security.token.TokenType;
import com.hepi.music_api.security.user.enums.UserStatus;
import com.hepi.music_api.security.user.model.User;
import com.hepi.music_api.security.user.repository.UserRepository;
import com.hepi.music_api.utills.HelperUtility;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        Optional<Role> roleOpt = roleRepository.findByRoleCode(request.getRoleId());
        if (roleOpt.isEmpty()) {
            throw new IllegalArgumentException("Role not found");
        }

        var user = User.builder()
                .firstname(request.getUserFirstname())
                .lastname(request.getUserLastname())
                .email(request.getUserEmail())
                .password(passwordEncoder.encode(request.getUserPassword()))
                .userPhoneNumber(request.getUserPhone())
                .userStatus(UserStatus.INACTIVE)
                .userIdNumber(request.getUserIdNumber())
                .userHashedMsisdn(HelperUtility.hashPhoneNumber(request.getUserPhone()))
                .role(roleOpt.get())
                .build();
        var savedUser = repository.save(user);

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = repository.findByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }
}
