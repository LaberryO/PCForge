package org.kamjeon.pcforge.PCpart.ComCase;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComCaseRepository extends JpaRepository<ComCase, Integer> {
	List<ComCase> findAll(Specification<ComCase> spec);
}
