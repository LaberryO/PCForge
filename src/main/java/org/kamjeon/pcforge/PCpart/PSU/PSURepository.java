package org.kamjeon.pcforge.PCpart.PSU;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PSURepository extends JpaRepository<PSU, Integer> {
	Page<PSU> findAll(Specification<PSU> spec, Pageable pageable);
	List<PSU> findAll(Specification<PSU> spec);
}
