package org.kamjeon.pcforge.User;

import java.util.Optional;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public SiteUser createUser(String name, String email, String password) {
		SiteUser user = new SiteUser();
		user.setUserName(name);
		user.setPassword(passwordEncoder.encode(password));
		user.setEmail(email);
		
		this.userRepository.save(user);
		return user;
	}
	public SiteUser getUser(String name) {
		
		Optional<SiteUser> _siteUser = this.userRepository.findByUserName(name);
		
		if(_siteUser.isPresent()) {
			return _siteUser.get();
		} else {
			throw new RuntimeException();
		}
	}
	
}
