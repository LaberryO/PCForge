package org.kamjeon.pcforge.PCpart;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BaseProduct {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String fileName;

    private Integer price;

    private Integer makeCompany;
    
    @JsonIgnore
	  @ManyToOne
	  @JoinColumn(name = "pcparts_id")
	  private PCParts pcPart;
}