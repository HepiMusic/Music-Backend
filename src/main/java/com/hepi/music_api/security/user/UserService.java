package com.hepi.music_api.security.user;


import com.hepi.music_api.common.Acknowledgement;
import com.hepi.music_api.security.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;


public interface UserService {


    void changePassword(ChangePasswordRequest request, Principal connectedUser);

    Page<User> findAll(Pageable pageable);
    User findByUserCode(Long userCode);


    Acknowledgement deleteUser(Long usercode);
}
