package org.kamjeon.pcforge.PCpart.MBoard;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MBoardRepository extends JpaRepository<MBoard, Integer> {
	List<MBoard> findAll(Specification<MBoard> spec);
}
