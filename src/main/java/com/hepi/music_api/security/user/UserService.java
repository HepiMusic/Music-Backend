package com.hepi.music_api.security.user;

import com.villagecan.villagecan_service.common.Acknowledgement;
import com.villagecan.villagecan_service.security.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.security.Principal;


public interface UserService {


    void changePassword(ChangePasswordRequest request, Principal connectedUser);

    Page<User> findAll(Pageable pageable);
    User findByUserCode(Integer userCode);


    Acknowledgement deleteUser(Integer usercode);
}
