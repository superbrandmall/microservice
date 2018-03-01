package com.sbm.module.onlineleasing.admin;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Data
public class TestDomain {

	@NotBlank()
	private String ttttt;


	@Min(value = 100)
	private String min;

	@Valid
	private Testddd1213 obj;

}
