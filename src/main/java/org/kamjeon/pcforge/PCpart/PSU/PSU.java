package org.kamjeon.pcforge.PCpart.PSU;

import org.kamjeon.pcforge.PCpart.BaseProduct;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

// 파워 서플라이
@Entity
@Getter
@Setter
public class PSU extends BaseProduct {


	private Integer wattage;

	private String efficency;

	private String formFactor;

}
