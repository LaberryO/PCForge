package org.kamjeon.pcforge.Board.Share;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.kamjeon.pcforge.Forge.Forge;
import org.kamjeon.pcforge.User.SiteUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ShareService {
	private final ShareRepository shareRepository;
	private int maxValue = 10;
	//세이브하는 기능
	public void save(Forge forge, String subject, String content, SiteUser user) {
		Share share = new Share();
		share.setSubject(subject);
		share.setContent(content);
		
		//사진들의 스트링값 
		List<String> images = forge.getFileNames();
		share.setFile(images);
		share.setTime(LocalDateTime.now());
		share.setUser(user);
		this.shareRepository.save(share);
	}
	
	//페이징하는 기능
	public Page<Share> getList(int page){
		List<Sort.Order> sorts = new ArrayList<>();
		
		//나중에 swich로 enum값 형채로 자동 정렬하기
		sorts.add(Sort.Order.desc("time")); //우선 작성한 날짜로 자동 정렬하여 페이징 처리
		Pageable pageable = PageRequest.of(page, maxValue, Sort.by(sorts));
		return this.shareRepository.findAll(pageable);
	}
	
	public Share getShare(int id) {
		Optional<Share> _share = this.shareRepository.findById(id);
		return _share.get();
	}
	
	public void modify(Share share, String subject, String content) {
		share.setSubject(subject);
		share.setContent(content);
		share.setModifyDate(LocalDateTime.now());
	
		this.shareRepository.save(share);
	}
	
	public void delete(Share share) {
		this.shareRepository.delete(share);
	}
}
