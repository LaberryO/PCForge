package org.kamjeon.pcforge.PCpart.Disk;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiskRepository extends JpaRepository<Disk, Integer> {
	List<Disk> findAll(Specification<Disk> spec);
}
