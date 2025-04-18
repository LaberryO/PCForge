package org.kamjeon.pcforge.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long>{
	Optional<SiteUser> findByUserName(String name);
}
