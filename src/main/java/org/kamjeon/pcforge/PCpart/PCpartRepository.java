package org.kamjeon.pcforge.PCpart;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PCpartRepository extends JpaRepository<PCParts, Integer>{
	
	Page<PCParts> findAll(Pageable pageable);
	Page<PCParts> findAll(Specification<PCParts> spec, Pageable pageable);
	
}
	