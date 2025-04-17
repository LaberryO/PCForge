package org.kamjeon.pcforge.PCpart.PSU;

import org.kamjeon.pcforge.PCpart.BaseProduct;
import org.kamjeon.pcforge.PCpart.PCParts;
import org.kamjeon.pcforge.PCpart.Company.Company;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
