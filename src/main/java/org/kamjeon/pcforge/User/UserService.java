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
	
	public void CreateUser(UserCreateForm userCreate) {
		SiteUser user = new SiteUser();
		user.setUserName(userCreate.getUsername());
		user.setPassword(passwordEncoder.encode(userCreate.getPassword1()));
		user.setEmail(userCreate.getEmail());
		
		this.userRepository.save(user);
	}
	public SiteUser GetUser(String name) {
		
		Optional<SiteUser> _siteUser = this.userRepository.findByUserName(name);
		
		if(_siteUser.isPresent()) {
			return _siteUser.get();
		}else {
			throw new RuntimeException();
		}
	}
	
}
