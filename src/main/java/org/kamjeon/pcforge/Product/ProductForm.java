package org.kamjeon.pcforge.Product;

import java.util.List;

import org.kamjeon.pcforge.PCpart.SearchType;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm {
	 // 부품에 따라 다르게 표시할 추가적인 입력 필드들
	
	@NotBlank
	@NotEmpty(message="이름은 필수 항목입니다.")
    private String name;
	
    @NotNull
    private MultipartFile fileName;
    
    @NotNull(message = "가격은 필수 항목입니다.")
    private Integer price;
    
    @NotNull(message = "제조사를 선택해야 합니다.")
    private Integer makeCompany;
    
    private List<MultipartFile> files;
    
	private SearchType search;

    // CPU 관련 입력 필드
    private Float defaultSpeed;
    private Float maxSpeed;
    private String socket;
    private Integer coreCount;
    private Integer threadCount;
    private Integer ddr;
    private Integer ddrSpeed;
    private Integer memoryChannel;
    private Boolean innerGPU;

    // Disk 관련 입력 필드
    private Integer capacity;
    private String type;
    private Integer speed;

    // GPU 관련 입력 필드
    private Integer memorySize;
    private Integer powerConsumption;

    // MBoard 관련 입력 필드
    private String formFactor;
    private Integer maxMemory;
    private String ddrSupport;
    private Integer PCISlots;

    // PSU 관련 입력 필드
    private Integer wattage;
    private String efficiency;
    private String psuFormFactor;

    // RAM 관련 입력 필드
    private Integer ramCapacity;
    private String ramType;
    private Integer ramSpeed;
    private Integer ramMemoryChannel;

    // ComCase 관련 입력 필드
    private String caseFormFactor;
    private String caseColor;
    private String caseMaterial;
    private Integer fanSupport;
}
