package com.sbm.module.common.authorization.init;

import com.sbm.module.common.init.InitAfterLoad;
import com.sbm.module.common.provider.SpringContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

@Component
@Slf4j
public class MethodRegister implements InitAfterLoad {

	@Autowired
	private SpringContextProvider provider;

	@Value("${authorization.method.basePackage}")
	private String methodBasePackage;
	@Value("${spring.application.name}")
	private String applicationName;

	private static final String BASE = "com.sbm.module";

	@Override
	public void init() {
		// 如果没有配置，则使用默认值
		String tmpPackage = methodBasePackage;
		if (StringUtils.isBlank(tmpPackage)) {
			tmpPackage = BASE + applicationName;
		}
		// 替换-为. 默认规则spring.application.name中存在- 对应包名用.
		final String basePackage = tmpPackage.replace("-", ".");
		// 获取所有RequestMappingHandlerMapping 如果需要其他再添加
		RequestMappingHandlerMapping requestMappingHandlerMapping = provider.getBean(RequestMappingHandlerMapping.class);
		Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
		map.keySet().forEach(e -> {
			HandlerMethod method = map.get(e);
			if (method.getBeanType().getName().startsWith(basePackage)) {
				e.getMethodsCondition().getMethods().forEach(a -> System.out.println(a));
				System.out.println(e.getPatternsCondition());
				System.out.println();
				System.out.println("**********************");
			}
		});
	}
}
