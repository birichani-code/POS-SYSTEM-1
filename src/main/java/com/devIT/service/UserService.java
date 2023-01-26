package com.devIT.service;
import com.devIT.entity.User;
import com.devIT.entity.security.PasswordResetToken;
import com.devIT.entity.security.UserRole;

import java.util.Set;

public interface UserService {
	PasswordResetToken getPasswordResetToken(final String token);
	
	void createPasswordResetTokenForUser(final User user, final String token);
	
	User findByUsername(String username);
	
	User findByEmail (String email);
	
	User findById(Long id);
	
	User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	User save(User user);
	

}
