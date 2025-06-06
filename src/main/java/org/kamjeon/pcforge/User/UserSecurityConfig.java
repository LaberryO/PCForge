package org.kamjeon.pcforge.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityConfig implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<SiteUser> _site = userRepository.findByUserName(username);
		if(_site.isEmpty()) {
			throw new  UsernameNotFoundException("사용자를 찾을 수 없습니다.");
		}
		
		SiteUser user = _site.get();
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if("Admin".equals(username)) {
			authorities.add(new SimpleGrantedAuthority(UserRole.Admin.getValue()));
		}else {
			authorities.add(new SimpleGrantedAuthority(UserRole.User.getValue()));
		}
		
		return new User(user.getUserName(), user.getPassword(), authorities);
	}

}
