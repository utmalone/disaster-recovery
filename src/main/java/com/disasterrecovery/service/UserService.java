package com.disasterrecovery.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.disasterrecovery.model.User;
import com.disasterrecovery.dto.*;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
