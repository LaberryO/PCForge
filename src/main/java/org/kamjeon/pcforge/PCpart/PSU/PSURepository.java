package org.kamjeon.pcforge.PCpart.PSU;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PSURepository extends JpaRepository<PSU, Integer> {
	List<PSU> findAll(Specification<PSU> spec);
}
