package org.kamjeon.pcforge.PCpart.RAM;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RAMRepository extends JpaRepository<RAM, Integer> {
	Page<RAM> findAll(Specification<RAM> spec, Pageable pageable);
	List<RAM> findAll(Specification<RAM> spec);
}
