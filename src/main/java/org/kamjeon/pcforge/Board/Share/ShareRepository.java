package org.kamjeon.pcforge.Board.Share;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareRepository extends JpaRepository<Share, Integer> {
	
	Page<Share> findAll(Pageable pageable);
}
