package com.sbm.module.onlineleasing.domain.register;

import lombok.Data;

@Data
public class StepSimpleResult extends StepOneResult {

	public StepSimpleResult() {
	}

	public StepSimpleResult(String code, String email, String mobile, Integer international) {
		super(code, email, mobile, international);
	}
}
