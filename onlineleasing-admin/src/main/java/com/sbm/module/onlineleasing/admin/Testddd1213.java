package com.sbm.module.onlineleasing.admin;

import lombok.Data;

import javax.validation.constraints.Max;

@Data
public class Testddd1213 {

	@Max(value = 100)
	private String max;

}
