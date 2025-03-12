package org.kamjeon.pcforge.PCpart.RAM;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RAMRepository extends JpaRepository<RAM, Integer> {
	List<RAM> findAll(Specification<RAM> spec);
}
