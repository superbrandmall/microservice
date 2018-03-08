package com.sbm.module.common.authorization.init;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.authorization.api.method.biz.IMethodService;
import com.sbm.module.common.authorization.api.method.client.IMethodClient;
import com.sbm.module.common.authorization.api.method.domain.Method;
import com.sbm.module.common.authorization.api.method.domain.MethodDetail;
import com.sbm.module.common.authorization.exception.MethodCode;
import com.sbm.module.common.biz.impl.CommonServiceImpl;
import com.sbm.module.common.domain.JsonContainer;
import com.sbm.module.common.exception.BusinessException;
import com.sbm.module.common.init.InitAfterLoad;
import com.sbm.module.common.provider.SpringContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

@Component
@Slf4j
public class MethodRegisterInit extends CommonServiceImpl implements InitAfterLoad {

	@Autowired
	private SpringContextProvider provider;

	@Value("${authorization.method.basePackage:}")
	private String methodBasePackage;
	@Value("${spring.application.name}")
	private String applicationName;

	@Autowired
	private IMethodClient client;

	private static final String BASE = "com.sbm.module.";
	private static final String CLIENT = ".client.";

	@Override
	public void init() {
		Method vo = new Method();
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
			// 方法类以basePackage开头，不包含.client.
			if (method.getBeanType().getName().startsWith(basePackage) && !method.getBeanType().getName().contains(CLIENT)) {
				if (e.getMethodsCondition().getMethods().size() != 1) {
					throw new BusinessException(MethodCode.M0001, new Object[]{method.getBeanType().getName(), method.getMethod().getName()});
				}
				if (e.getPatternsCondition().getPatterns().size() != 1) {
					throw new BusinessException(MethodCode.M0002, new Object[]{method.getBeanType().getName(), method.getMethod().getName()});
				}
				RequestMethod requestMethod = e.getMethodsCondition().getMethods().iterator().next();
				String pattern = e.getPatternsCondition().getPatterns().iterator().next();
				// 加入列表
				vo.getDetails().add(new MethodDetail(applicationName, requestMethod.toString(), pattern, null));
			}
		});
		IMethodService service = null;
		try {
			service = provider.getBean(IMethodService.class);
		} catch (Exception ex) {

		}
		if (null != service) {
			service.register(vo);
			log(vo);
		} else {
			JsonContainer<Method> result = client.register(vo);
			// TODO 添加result C0 判断
			//		if (result.getCode()) {
			//
			//		}
			log(result.getData());
		}
	}

	/**
	 * 记录日志
	 *
	 * @param result
	 */
	private void log(Method result) {
		for (MethodDetail methodDetail : result.getDetails()) {
			log.info(JSON.toJSONString(methodDetail));
		}
	}

}
