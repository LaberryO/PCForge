package org.kamjeon.pcforge.Boards;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StarterRepository extends JpaRepository<StarterBoard, Integer> {
	
	 List<StarterBoard> findTop4ByTypeOrderByIdDesc(StarterType type);
}
