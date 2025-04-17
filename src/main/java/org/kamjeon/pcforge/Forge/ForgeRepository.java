package org.kamjeon.pcforge.Forge;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ForgeRepository extends JpaRepository<Forge, Integer> {
	Optional<Forge> findBySessionId(String sessionId);
}
