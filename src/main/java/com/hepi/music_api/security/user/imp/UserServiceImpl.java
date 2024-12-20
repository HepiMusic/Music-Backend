package com.hepi.music_api.security.user.imp;


import com.hepi.music_api.common.Acknowledgement;
import com.hepi.music_api.security.user.ChangePasswordRequest;
import com.hepi.music_api.security.user.UserService;
import com.hepi.music_api.security.user.model.User;
import com.hepi.music_api.security.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Calendar;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.repository = userRepository;

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        repository.save(user);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public User findByUserCode(Integer userCode) {
        return repository.findById(userCode).orElseThrow(() ->
                new IllegalStateException("User not found with code : "+ userCode));
    }

    @Override
    public Acknowledgement deleteUser(Integer usercode) {
        User user = repository.findById(usercode).orElseThrow(() -> new IllegalStateException("User not found with code : "+ usercode));
        repository.delete(user);
        return Acknowledgement
                .builder()
                .customizedMessage("deleted user")
                .responseMessage("success")
                .responseCode(200)
                .transactionId(UUID.randomUUID().toString())
                .timestamp(Calendar.getInstance().getTime().toString())
                .build();
    }


}


