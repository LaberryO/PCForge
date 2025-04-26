package org.kamjeon.pcforge.Forge;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ForgeRepository extends JpaRepository<Forge, Integer> {
	List<Forge> findAllBySessionId(String sessionId);
	Forge findTopBySessionIdOrderByIdDesc(String sessionId); // 가장 최근 Forge 가져오기용
}
