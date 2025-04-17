package org.kamjeon.pcforge.Boards;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StarterRepository extends JpaRepository<StarterBoard, Integer> {
	
	 List<StarterBoard> findTop4ByTypeOrderByIdDesc(StarterType type);
		
	Page<StarterBoard> findAll(Pageable pageable);
	Page<StarterBoard> findAll(Specification<StarterBoard> spec, Pageable pageable);
}
