package org.kamjeon.pcforge.PCpart.CPU;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CPURepository extends JpaRepository<CPU, Integer> {
	Page<CPU> findAll(Specification<CPU> spec, Pageable pageable);
	List<CPU> findAll(Specification<CPU> spec);
}
