package com.sbm.module.onlineleasing.init;

import com.sbm.module.common.authorization.api.passport.domain.Register;

public enum UserEnum {
	ADMIN(new Register("onlineleasing@superbrandmall.com", "admin", "12345678", 1, 0, 1, 0));

	private Register register;

	UserEnum(Register register) {
		this.register = register;
	}

	public Register getRegister() {
		return register;
	}

	public void setRegister(Register register) {
		this.register = register;
	}
}
