package org.kamjeon.pcforge.Product;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.kamjeon.pcforge.PCpart.CPU.CPU;
import org.kamjeon.pcforge.PCpart.CPU.CPURepository;
import org.kamjeon.pcforge.PCpart.ComCase.ComCase;
import org.kamjeon.pcforge.PCpart.ComCase.ComCaseRepository;
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
	private final CPURepository cpuRep;
	private final DiskRepository diskService;
	private final GPURepository gpuService;
	private final MBoardRepository mBoardService;
	private final PSURepository psuService;
	private final RAMRepository ramService;
	private final ComCaseRepository comCaseService;

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("selectForm", new ProductForm());
		return "productSelect";
	}

	@PreAuthorize("isAuthenticated()")
	@PostMapping("/save/{type}")
	public String save(@Valid ProductForm productForm, BindingResult bind, @PathVariable("type") String type, Principal principal) {
		if (bind.hasErrors()) {
			return "productForm";
		}

		List<String> savedFilePaths = saveFiles(productForm.getFiles());

		switch (type.toUpperCase()) {
			case "CPU" -> {
				CPU cpu = new CPU();
				cpu.setName(productForm.getName());
				cpu.setPrice(productForm.getPrice());
				cpu.setMakeCompany(productForm.getMakeCompany());
				cpu.setFiles(savedFilePaths);
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
				disk.setFiles(savedFilePaths);
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
				gpu.setFiles(savedFilePaths);
				gpu.setMemorySize(productForm.getMemorySize());
				gpu.setPowerConsumption(productForm.getPowerConsumption());

				gpuService.save(gpu);
			}
			case "MBOARD" -> {
				MBoard mboard = new MBoard();
				mboard.setName(productForm.getName());
				mboard.setPrice(productForm.getPrice());
				mboard.setMakeCompany(productForm.getMakeCompany());
				mboard.setFiles(savedFilePaths);
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
				psu.setFiles(savedFilePaths);
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
				ram.setFiles(savedFilePaths);
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
				comCase.setFiles(savedFilePaths);
				comCase.setFormFactor(productForm.getCaseFormFactor());
				comCase.setColor(productForm.getCaseColor());
				comCase.setMaterial(productForm.getCaseMaterial());
				comCase.setFanSupport(productForm.getFanSupport());

				comCaseService.save(comCase);
			}
			default -> throw new IllegalArgumentException("지원되지 않는 타입: " + type);
		}

		return "redirect:/";
	}

	private List<String> saveFiles(List<MultipartFile> files) {
		List<String> filePaths = new ArrayList<>();
		String uploadDir = "src/main/resources/static/Images/";

		for (MultipartFile file : files) {
			if (!file.isEmpty()) {
				try {
					String originalFilename = file.getOriginalFilename();
					String uuid = UUID.randomUUID().toString();
					String savedFileName = uuid + "_" + originalFilename;

					File dest = new File(uploadDir, savedFileName);
					file.transferTo(dest);

					filePaths.add("/uploads/" + savedFileName);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return filePaths;
	}
}
