package com.sbm.module.common.authorization.init;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.authorization.api.serialcode.biz.ISerialCodeRegisterService;
import com.sbm.module.common.authorization.api.serialcode.client.ISerialCodeClient;
import com.sbm.module.common.authorization.api.serialcode.constant.ISerialCode;
import com.sbm.module.common.authorization.api.serialcode.domain.SerialCode;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
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
@Order(1)
public class SerialCodeInitInit extends CommonServiceImpl implements InitAfterLoad {

	@Autowired
	private SpringContextProvider provider;

	@Autowired
	private ISerialCodeClient client;

	private static final String BASE = "com.sbm.module.";

	@Override
	public void init() {
		List<SerialCode> vos = new ArrayList<>();
		// 反射获取base路径下IBusinessCode的实现类
		Reflections reflections = new Reflections(BASE);
		Set<Class<? extends ISerialCode>> set = reflections.getSubTypesOf(ISerialCode.class);
		for (Class<? extends ISerialCode> clazz : set) {
			// 判断枚举
			if (clazz.isEnum()) {
				ISerialCode[] serialCodes = clazz.getEnumConstants();
				// 遍历枚举实例
				for (ISerialCode serialCode : serialCodes) {
					vos.add(convert(serialCode));
				}
			}
		}
		ISerialCodeRegisterService service = null;
		// ISerialCodeRegisterService
		try {
			service = provider.getBean(ISerialCodeRegisterService.class);
		} catch (Exception ex) {

		}
		// 有就用
		if (null != service) {
			service.register(vos);
			log(vos);
		}
		// 没有就调服务
		else {
			List<SerialCode> result = checkJsonContainer(client.register(vos));
			log(result);
		}
	}

	private SerialCode convert(ISerialCode serialCode) {
		SerialCode vo = new SerialCode(serialCode.getSerialGroup(), serialCode.getRemark());
		return vo;
	}

	/**
	 * 记录日志
	 *
	 * @param result
	 */
	private void log(List<SerialCode> result) {
		for (SerialCode vo : result) {
			log.info(JSON.toJSONString(vo));
		}
	}
}
