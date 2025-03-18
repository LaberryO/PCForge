package org.kamjeon.pcforge.PCpart.GPU;

import org.kamjeon.pcforge.PCpart.Disk.Disk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GPURepository extends JpaRepository<GPU, Integer> {
	Page<GPU> findAll(Specification<GPU> spec, Pageable pageable);
}
