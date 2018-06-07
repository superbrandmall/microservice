package com.sbm.module.common.authorization.init;

import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.exception.IBusinessCode;
import com.sbm.module.common.init.InitAfterLoad;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Slf4j
@Order(3)
public class BusinessCodeRegisterInit extends CommonServiceImpl implements InitAfterLoad {

	private static final String BASE = "com.sbm.module";

	@Override
	public void init() {
		Reflections reflections = new Reflections(BASE);
		Set<Class<? extends IBusinessCode>> set = reflections.getSubTypesOf(IBusinessCode.class);
		for (Class<? extends IBusinessCode> clazz : set) {
			if (clazz.isEnum()) {
				IBusinessCode[] businessCodes = clazz.getEnumConstants();
				for (IBusinessCode businessCode : businessCodes) {
					System.out.println(businessCode.getClazz() + businessCode.getCode() + businessCode.getMessage() + businessCode.getCustomerMessage());
				}
			}
		}
	}
}
