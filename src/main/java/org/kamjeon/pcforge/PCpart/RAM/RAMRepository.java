package org.kamjeon.pcforge.PCpart.RAM;

import org.kamjeon.pcforge.PCpart.PSU.PSU;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RAMRepository extends JpaRepository<RAM, Integer> {
	Page<RAM> findAll(Specification<RAM> spec, Pageable pageable);
}
