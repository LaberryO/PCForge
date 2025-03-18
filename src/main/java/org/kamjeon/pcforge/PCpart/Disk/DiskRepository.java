package org.kamjeon.pcforge.PCpart.Disk;

import org.kamjeon.pcforge.PCpart.ComCase.ComCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiskRepository extends JpaRepository<Disk, Integer> {
	Page<Disk> findAll(Specification<Disk> spec, Pageable pageable);
}
