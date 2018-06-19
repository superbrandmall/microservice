package com.sbm.module.common.authorization.init;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.authorization.api.businesscode.biz.IBusinessCodeRegisterService;
import com.sbm.module.common.authorization.api.businesscode.client.IBusinessCodeClient;
import com.sbm.module.common.authorization.api.businesscode.domain.BusinessCode;
import com.sbm.module.common.authorization.api.businesscode.domain.BusinessCodeLang;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.exception.IBusinessCode;
import com.sbm.module.common.init.InitAfterLoad;
import com.sbm.module.common.provider.SpringContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
@Order(3)
public class BusinessCodeRegisterInit extends CommonServiceImpl implements InitAfterLoad {

	@Autowired
	private SpringContextProvider provider;

	@Autowired
	private IBusinessCodeClient client;

	private static final String BASE = "com.sbm.module.";

	private static final String LANG = "zh-cn";

	@Override
	public void init() {
		List<BusinessCode> vos = new ArrayList<>();
		// 反射获取base路径下IBusinessCode的实现类
		Reflections reflections = new Reflections(BASE);
		Set<Class<? extends IBusinessCode>> set = reflections.getSubTypesOf(IBusinessCode.class);
		for (Class<? extends IBusinessCode> clazz : set) {
			// 判断枚举
			if (clazz.isEnum()) {
				IBusinessCode[] businessCodes = clazz.getEnumConstants();
				// 遍历枚举实例
				for (IBusinessCode businessCode : businessCodes) {
					vos.add(convert(businessCode));
				}
			}
		}
		IBusinessCodeRegisterService service = null;
		// 判断本地是否有IBusinessCodeRegisterService实现
		try {
			service = provider.getBean(IBusinessCodeRegisterService.class);
		} catch (Exception ex) {

		}
		// 有就用
		if (null != service) {
			service.register(vos);
			log(vos);
		}
		// 没有就调服务
		else {
			List<BusinessCode> result = checkJsonContainer(client.register(vos));
			log(result);
		}
	}

	private BusinessCode convert(IBusinessCode businessCode) {
		BusinessCode vo = new BusinessCode(businessCode.getClazz(), businessCode.getCode(), businessCode.getMessage(), businessCode.getCustomerMessage());
		vo.getBusinessCodeLangs().add(new BusinessCodeLang(LANG, businessCode.getCustomerMessage()));
		return vo;
	}

	/**
	 * 记录日志
	 *
	 * @param result
	 */
	private void log(List<BusinessCode> result) {
		for (BusinessCode vo : result) {
			log.info(JSON.toJSONString(vo));
		}
	}
}
