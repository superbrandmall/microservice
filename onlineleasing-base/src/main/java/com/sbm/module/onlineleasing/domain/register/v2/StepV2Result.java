package com.sbm.module.onlineleasing.domain.register.v2;

import com.sbm.module.onlineleasing.domain.register.StepOneResult;
import lombok.Data;

@Data
public class StepV2Result extends StepOneResult {

	public StepV2Result() {
	}

	public StepV2Result(String code, String email, String mobile, Integer international) {
		super(code, email, mobile, international);
	}
}
