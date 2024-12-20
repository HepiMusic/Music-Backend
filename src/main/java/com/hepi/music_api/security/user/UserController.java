package com.hepi.music_api.security.user;


import com.hepi.music_api.common.Acknowledgement;
import com.hepi.music_api.security.user.model.User;
import com.hepi.music_api.security.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    private final UserService service;

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<User>> getAllUsers(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{usercode}")
    public ResponseEntity<User> getUserByUserCode(@PathVariable Integer usercode) {
        return ResponseEntity.ok(service.findByUserCode(usercode));
    }


    @DeleteMapping("/{usercode}")
    public ResponseEntity<Acknowledgement> deleteUser(@PathVariable Integer usercode) {
        return ResponseEntity.ok(service.deleteUser(usercode));
    }

}
