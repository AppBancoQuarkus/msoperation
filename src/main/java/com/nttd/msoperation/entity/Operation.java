package com.nttd.msoperation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "Operation")
public class Operation {
	
	
	@Id
	@GeneratedValue
	private Integer idOperation;
	private Double ammount;
	private Integer idAOperationOrigin;
	private String flagOperation;
	private String flagdDescription;
	private String description;
	private Integer idAccountCustomer;
	
	
	
}
