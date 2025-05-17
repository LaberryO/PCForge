package org.kamjeon.pcforge.Boards;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StarterService {
	private final StarterRepository starterRepository;
	private static final String UPLOAD_DIR = "home/ubuntu/upload/";

	public void create(String tile, String fileName, String content, StarterType type) {
		StarterBoard s = new StarterBoard();
		s.setTitle(tile);
		s.setContent(content);
		s.setImage(fileName);
		s.setCreateDate(LocalDateTime.now());
		s.setType(type);
		this.starterRepository.save(s);
	}

	public StarterBoard getStarterBoard(int id) {
		Optional<StarterBoard> starter = this.starterRepository.findById(id);
		if (starter.isPresent())
			return starter.get();

		return null;
	}

	public String saveIamge(MultipartFile image) throws IOException {

	    if (image.isEmpty()) {
	        return null;  // 이미지가 비었으면 null 반환
	    }

	    // 파일 이름 생성
	    String original = image.getOriginalFilename();
	    String fileName = System.currentTimeMillis() + "_" + original;

	    // 파일 업로드 디렉토리 지정
	    File uploadDir = new File(UPLOAD_DIR);
	    if (!uploadDir.exists()) {
	        uploadDir.mkdirs();  // 디렉토리가 없다면 생성
	    }

	    // 파일 경로 결합 (UPLOAD_DIR이 절대 경로로 설정되어 있다고 가정)
	    Path filePath = Paths.get(UPLOAD_DIR, fileName);  // 경로 결합을 Paths.get()으로 수정

	    // 파일 복사
	    Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	    // 업로드된 파일을 웹에서 접근할 수 있는 URL로 반환 (상대 경로로 반환)
	    return "/upload/" + fileName;  // "/upload/" 경로로 웹에서 접근 가능하게 반환
	}

	public List<List<StarterBoard>> partitionList(List<StarterBoard> list, int chunkSize) {
		List<List<StarterBoard>> partitionedList = new ArrayList<>();
		for (int i = 0; i < list.size(); i += chunkSize) {
			partitionedList.add(list.subList(i, Math.min(i + chunkSize, list.size())));
		}
		return partitionedList;
	}
	
	public Page<StarterBoard> getList(int page, String kw){
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, 6,  Sort.by(sorts));
		Specification<StarterBoard> spec = search(kw);
		return this.starterRepository.findAll(spec, pageable);
	}

	private Specification<StarterBoard> search(String kw) {
	    return (Root<StarterBoard> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
	        // 검색어가 없으면 모든 결과를 반환
	        if (kw == null || kw.isEmpty()) {
	            return cb.conjunction(); // 항상 참을 반환
	        }
	        
	        Predicate titlePredicate = cb.like(cb.lower(p.get("title")), "%" + kw.toLowerCase() + "%");

	        return titlePredicate;
	    };
	}

}
