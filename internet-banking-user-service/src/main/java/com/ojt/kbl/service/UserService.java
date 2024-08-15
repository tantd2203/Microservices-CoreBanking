package com.ojt.kbl.service;

import com.ojt.kbl.dto.NewUserRecord;

public interface UserService {

    void createUser(NewUserRecord newUserRecord);
    void sendVerificationEmail(String userId);
    void deleteUser(String userId);
    void forgotPassword(String email);


}
