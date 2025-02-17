package org.kamjeon.pcforge.User;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	
	public SiteUser GetUser(String name) {
		
		Optional<SiteUser> _siteUser = this.userRepository.findByUserName(name);
		
		if(_siteUser.isPresent()) {
			return _siteUser.get();
		}else {
			throw new RuntimeException();
		}
	}
	
}
