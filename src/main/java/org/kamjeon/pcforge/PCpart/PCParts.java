package org.kamjeon.pcforge.PCpart;

import java.util.List;

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
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PCParts {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동 증가 설정
    private Integer pcparts_id;

    @OneToMany(mappedBy = "pcPart", cascade = CascadeType.ALL) // 자식 객체들이 모두 연결되도록 설정
    private List<ComCase> comCase;

    @OneToMany(mappedBy = "pcPart", cascade = CascadeType.ALL)
    private List<CPU> cpu;

    @OneToMany(mappedBy = "pcPart", cascade = CascadeType.ALL)
    private List<Disk> disk;

    @OneToMany(mappedBy = "pcPart", cascade = CascadeType.ALL)
    private List<GPU> gpu;

    @OneToMany(mappedBy = "pcPart", cascade = CascadeType.ALL)
    private List<MBoard> mBoard;

    @OneToMany(mappedBy = "pcPart", cascade = CascadeType.ALL)
    private List<PSU> psu;

    @OneToMany(mappedBy = "pcPart", cascade = CascadeType.ALL)
    private List<RAM> ram;

    @OneToMany(mappedBy = "pcPart", cascade = CascadeType.ALL)
    private List<Company> company;
	 
}
