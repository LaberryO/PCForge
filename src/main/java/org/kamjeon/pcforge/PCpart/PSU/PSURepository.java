package org.kamjeon.pcforge.PCpart.PSU;

import org.kamjeon.pcforge.PCpart.MBoard.MBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PSURepository extends JpaRepository<PSU, Integer> {
	Page<PSU> findAll(Specification<PSU> spec, Pageable pageable);
}
