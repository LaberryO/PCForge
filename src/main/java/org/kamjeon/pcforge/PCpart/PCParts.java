package org.kamjeon.pcforge.PCpart;



import org.kamjeon.pcforge.PCpart.CPU.CPU;
import org.kamjeon.pcforge.PCpart.ComCase.ComCase;
import org.kamjeon.pcforge.PCpart.Company.Company;
import org.kamjeon.pcforge.PCpart.Disk.Disk;
import org.kamjeon.pcforge.PCpart.GPU.GPU;
import org.kamjeon.pcforge.PCpart.MBoard.MBoard;
import org.kamjeon.pcforge.PCpart.PSU.PSU;
import org.kamjeon.pcforge.PCpart.RAM.RAM;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PCParts {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 증가 설정
    private Integer id;

    @OneToOne(mappedBy = "pcPart", cascade = CascadeType.ALL)  // ComCase는 단일 객체 참조
    private ComCase comCase;

    @OneToOne(mappedBy = "pcPart", cascade = CascadeType.ALL)  // CPU는 단일 객체 참조
    private CPU cpu;

    @OneToOne(mappedBy = "pcPart", cascade = CascadeType.ALL)  // Disk는 단일 객체 참조
    private Disk disk;

    @OneToOne(mappedBy = "pcPart", cascade = CascadeType.ALL)  // GPU는 단일 객체 참조
    private GPU gpu;

    @OneToOne(mappedBy = "pcPart", cascade = CascadeType.ALL)  // MBoard는 단일 객체 참조
    private MBoard mBoard;

    @OneToOne(mappedBy = "pcPart", cascade = CascadeType.ALL)  // PSU는 단일 객체 참조
    private PSU psu;

    @OneToOne(mappedBy = "pcPart", cascade = CascadeType.ALL)  // RAM은 단일 객체 참조
    private RAM ram;

    @OneToOne(mappedBy = "pcPart", cascade = CascadeType.ALL)  // Company는 단일 객체 참조
    private Company company;
	 
}
