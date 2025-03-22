package org.kamjeon.pcforge.Boards;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StarterService {
	private final StarterRepository starterRepository;
	
	public void create(String tile, String fileName, String content) {
		StarterBoard s = new StarterBoard();
		s.setTitle(tile);
		s.setContent(content);
		s.setImage(fileName);
		this.starterRepository.save(s);
	}
}
