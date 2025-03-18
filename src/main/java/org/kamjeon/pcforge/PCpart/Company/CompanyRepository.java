package org.kamjeon.pcforge.PCpart.Company;

import org.kamjeon.pcforge.PCpart.ComCase.ComCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
	Page<ComCase> findAll(Specification<ComCase> spec, Pageable pageable);
}
