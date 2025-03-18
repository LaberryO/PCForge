package org.kamjeon.pcforge.PCpart.ComCase;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComCaseRepository extends JpaRepository<ComCase, Integer> {
	Page<ComCase> findAll(Specification<ComCase> spec, Pageable pageable);
	List<ComCase> findAll(Specification<ComCase> spec); 
}
