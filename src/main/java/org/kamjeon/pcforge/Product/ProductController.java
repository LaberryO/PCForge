package org.kamjeon.pcforge.Product;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.kamjeon.pcforge.PCpart.BaseProduct;
import org.kamjeon.pcforge.PCpart.BaseProductRepository;
import org.kamjeon.pcforge.PCpart.CPU.CPU;
import org.kamjeon.pcforge.PCpart.CPU.CPURepository;
import org.kamjeon.pcforge.PCpart.ComCase.ComCase;
import org.kamjeon.pcforge.PCpart.ComCase.ComCaseRepository;
import org.kamjeon.pcforge.PCpart.Company.CompanyRepository;
import org.kamjeon.pcforge.PCpart.Disk.Disk;
import org.kamjeon.pcforge.PCpart.Disk.DiskRepository;
import org.kamjeon.pcforge.PCpart.GPU.GPU;
import org.kamjeon.pcforge.PCpart.GPU.GPURepository;
import org.kamjeon.pcforge.PCpart.MBoard.MBoard;
import org.kamjeon.pcforge.PCpart.MBoard.MBoardRepository;
import org.kamjeon.pcforge.PCpart.PSU.PSU;
import org.kamjeon.pcforge.PCpart.PSU.PSURepository;
import org.kamjeon.pcforge.PCpart.RAM.RAM;
import org.kamjeon.pcforge.PCpart.RAM.RAMRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/product")
@Controller
@RequiredArgsConstructor
public class ProductController {
	private static final String uploadDir = "src/main/resources/static/Images/";
	private final BaseProductRepository baseRep;
	private final CPURepository cpuRep;
	private final DiskRepository diskService;
	private final GPURepository gpuService;
	private final MBoardRepository mBoardService;
	private final PSURepository psuService;
	private final RAMRepository ramService;
	private final ComCaseRepository comCaseService;
	private final CompanyRepository comRep;

	@GetMapping("/create")
	public String create(ProductForm productForm) {
		/* model.addAttribute("selectForm", new ProductForm()); */
		return "productSelect";
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/save/{type}")
	public String save(Model model, @Valid ProductForm productForm, BindingResult bind, @PathVariable("type") String type, Principal prince) throws IOException {
		
		MultipartFile image = productForm.getFileName();
		if (image == null || image.isEmpty()) {
			bind.rejectValue("fileName", "error.fileName", "이미지를 업로드해야 합니다.");
		}
		if (bind.hasErrors()) {
			System.out.println("오류가 난다 나");
			model.addAttribute("selectForm", productForm);
			return "productSelect";
		}

		List<String> savedFilePaths = saveFiles(productForm.getFiles());

		switch (type.toUpperCase()) {
		case "CPU" -> {
			CPU cpu = new CPU();
			cpu.setName(productForm.getName());
			cpu.setPrice(productForm.getPrice());
			cpu.setMakeCompany(productForm.getMakeCompany());
			cpu.setFileName(saveImage(productForm.getFileName()));
			cpu.setFiles(savedFilePaths);
			cpu.setManager(prince.getName());
			cpu.setDefaultSpeed(productForm.getDefaultSpeed());
			cpu.setMaxSpeed(productForm.getMaxSpeed());
			cpu.setSocket(productForm.getSocket());
			cpu.setCoreCount(productForm.getCoreCount());
			cpu.setThreadCount(productForm.getThreadCount());
			cpu.setDdr(productForm.getDdr());
			cpu.setDdrSpeed(productForm.getDdrSpeed());
			cpu.setMemoryChannel(productForm.getMemoryChannel());
			cpu.setInnerGPU(productForm.getInnerGPU());

			cpuRep.save(cpu);
		}
		case "DISK" -> {
			Disk disk = new Disk();
			disk.setName(productForm.getName());
			disk.setPrice(productForm.getPrice());
			disk.setMakeCompany(productForm.getMakeCompany());
			disk.setFileName(saveImage(productForm.getFileName()));
			disk.setFiles(savedFilePaths);
			disk.setManager(prince.getName());
			disk.setCapacity(productForm.getCapacity());
			disk.setType(productForm.getType());
			disk.setSpeed(productForm.getSpeed());

			diskService.save(disk);
		}
		case "GPU" -> {
			GPU gpu = new GPU();
			gpu.setName(productForm.getName());
			gpu.setPrice(productForm.getPrice());
			gpu.setMakeCompany(productForm.getMakeCompany());
			gpu.setFileName(saveImage(productForm.getFileName()));
			gpu.setFiles(savedFilePaths);
			gpu.setManager(prince.getName());
			gpu.setMemorySize(productForm.getMemorySize());
			gpu.setPowerConsumption(productForm.getPowerConsumption());

			gpuService.save(gpu);
		}
		case "MBOARD" -> {
			MBoard mboard = new MBoard();
			mboard.setName(productForm.getName());
			mboard.setPrice(productForm.getPrice());
			mboard.setMakeCompany(productForm.getMakeCompany());
			mboard.setFileName(saveImage(productForm.getFileName()));
			mboard.setFiles(savedFilePaths);
			mboard.setManager(prince.getName());
			mboard.setFormFactor(productForm.getFormFactor());
			mboard.setMaxMemory(productForm.getMaxMemory());
			mboard.setDdrSupport(productForm.getDdrSupport());
			mboard.setPCISlots(productForm.getPCISlots());

			mBoardService.save(mboard);
		}
		case "PSU" -> {
			PSU psu = new PSU();
			psu.setName(productForm.getName());
			psu.setPrice(productForm.getPrice());
			psu.setMakeCompany(productForm.getMakeCompany());
			psu.setFileName(saveImage(productForm.getFileName()));
			psu.setFiles(savedFilePaths);
			psu.setManager(prince.getName());
			psu.setWattage(productForm.getWattage());
			psu.setEfficency(productForm.getEfficiency());
			psu.setFormFactor(productForm.getPsuFormFactor());

			psuService.save(psu);
		}
		case "RAM" -> {
			RAM ram = new RAM();
			ram.setName(productForm.getName());
			ram.setPrice(productForm.getPrice());
			ram.setMakeCompany(productForm.getMakeCompany());
			ram.setFileName(saveImage(productForm.getFileName()));
			ram.setFiles(savedFilePaths);
			ram.setManager(prince.getName());
			ram.setCapacity(productForm.getRamCapacity());
			ram.setType(productForm.getRamType());
			ram.setSpeed(productForm.getRamSpeed());
			ram.setMemoryChannel(productForm.getRamMemoryChannel());

			ramService.save(ram);
		}
		case "COMCASE" -> {
			ComCase comCase = new ComCase();
			comCase.setName(productForm.getName());
			comCase.setPrice(productForm.getPrice());
			comCase.setMakeCompany(productForm.getMakeCompany());
			comCase.setFileName(saveImage(productForm.getFileName()));
			comCase.setFiles(savedFilePaths);
			comCase.setManager(prince.getName());
			comCase.setFormFactor(productForm.getCaseFormFactor());
			comCase.setColor(productForm.getCaseColor());
			comCase.setMaterial(productForm.getCaseMaterial());
			comCase.setFanSupport(productForm.getFanSupport());

			comCaseService.save(comCase);
		}
	}


		return "redirect:/";
	}
	
	@GetMapping("/delete")
	public String delete(Principal prince, Model model) {
	    String managerName = prince.getName();

	    List<BaseProduct> products = baseRep.findByManager(managerName);

	    // 회사 이름을 매핑하기 위한 Map 생성
	    Map<Integer, String> companyNameMap = new HashMap<>();

	    for (BaseProduct product : products) {
	        int companyId = product.getMakeCompany(); // 회사 ID로 가정
	        if (!companyNameMap.containsKey(companyId)) {
	        	comRep.findById(companyId).ifPresent(company ->
	                companyNameMap.put(companyId, company.getName())
	            );
	        }
	    }

	    model.addAttribute("products", products);
	    model.addAttribute("companyNames", companyNameMap);

	    return "shop_delete";
	}
	
	@GetMapping("/delete/{id}")
	public String productDelete(@PathVariable("id") int id) {
		
		Optional<BaseProduct> board = this.baseRep.findById(id);
		BaseProduct starter = board.get();
		
		this.baseRep.delete(starter);
		
		return "redirect:/product/delete";
	}

	private List<String> saveFiles(List<MultipartFile> files) {
	    List<String> fileNames = new ArrayList<>();

	    for (MultipartFile file : files) {
	        if (!file.isEmpty()) {
	            try {
	                String savedFileName = saveImage(file); // ⬅️ 여기서 파일 저장
	                if (savedFileName != null) {
	                    fileNames.add(savedFileName); // ⬅️ 결과 파일명 저장
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }

	    return fileNames;
	}
	
	
	private String saveImage(MultipartFile image) throws IOException {

		if (image.isEmpty())
			return null;

		String original = image.getOriginalFilename();
		String fileName = System.currentTimeMillis() + "_" + original;

		File upload = new File(uploadDir);
		if (!upload.exists())
			upload.mkdirs();

		Path filePath = Paths.get(uploadDir + fileName);
		Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

		return fileName;
	}
}
