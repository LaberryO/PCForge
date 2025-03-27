package org.kamjeon.pcforge.Boards;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StarterService {
	private final StarterRepository starterRepository;
	 private static final String UPLOAD_DIR = "src/main/resources/static/uploads/";
	 
	public void create(String tile, String fileName, String content, StarterType type) {
		StarterBoard s = new StarterBoard();
		s.setTitle(tile);
		s.setContent(content);
		s.setImage(fileName);
		s.setType(type);
		this.starterRepository.save(s);
	}
	
	public String saveIamge(MultipartFile image) throws IOException {
		
		if(image.isEmpty())
			return null;
		
		String original = image.getOriginalFilename();
		String fileName = System.currentTimeMillis() +"_" + original;
		
		File uploadDir = new File(UPLOAD_DIR);
		if(!uploadDir.exists())
			uploadDir.mkdirs();
		
		Path filePath = Paths.get(UPLOAD_DIR + fileName);
		Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
		
		return fileName;
	}
}
