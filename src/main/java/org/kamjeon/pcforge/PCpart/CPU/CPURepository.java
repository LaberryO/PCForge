package org.kamjeon.pcforge.PCpart.CPU;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CPURepository extends JpaRepository<CPU, Integer> {
	List<CPU> findAll(Specification<CPU> spec);
}
