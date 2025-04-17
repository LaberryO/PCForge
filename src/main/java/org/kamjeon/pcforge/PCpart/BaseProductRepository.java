package org.kamjeon.pcforge.PCpart;

import java.util.List;

import org.kamjeon.pcforge.PCpart.ComCase.ComCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseProductRepository extends JpaRepository<BaseProduct, Integer> {
	Page<BaseProduct> findAll(Specification<BaseProduct> spec, Pageable pageable);
	List<BaseProduct> findAll(Specification<BaseProduct> spec); 
}
