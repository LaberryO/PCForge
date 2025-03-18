package org.kamjeon.pcforge.PCpart.MBoard;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MBoardRepository extends JpaRepository<MBoard, Integer> {
	Page<MBoard> findAll(Specification<MBoard> spec, Pageable pageable);
	List<MBoard> findAll(Specification<MBoard> spec);
}	
