package org.kamjeon.pcforge.PCpart.GPU;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GPURepository extends JpaRepository<GPU, Integer> {
	List<GPU> findAll(Specification<GPU> spec);
}
