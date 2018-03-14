package com.sbm.module.common.authorization.init;

import com.alibaba.fastjson.JSON;
import com.sbm.module.common.authorization.api.method.biz.IMethodRegisterService;
import com.sbm.module.common.authorization.api.method.client.IMethodClient;
import com.sbm.module.common.authorization.api.method.constant.MethodConstant;
import com.sbm.module.common.authorization.api.method.domain.Method;
import com.sbm.module.common.authorization.exception.AuthorizationCode;
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

import java.util.ArrayList;
import java.util.List;
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
		List<Method> vos = new ArrayList<>();
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
					throw new BusinessException(AuthorizationCode.M0001, new Object[]{method.getBeanType().getName(), method.getMethod().getName()});
				}
				if (e.getPatternsCondition().getPatterns().size() != 1) {
					throw new BusinessException(AuthorizationCode.M0002, new Object[]{method.getBeanType().getName(), method.getMethod().getName()});
				}
				RequestMethod requestMethod = e.getMethodsCondition().getMethods().iterator().next();
				String pattern = e.getPatternsCondition().getPatterns().iterator().next();
				// 加入列表
				vos.add(new Method(applicationName, requestMethod.toString(), pattern, MethodConstant.VALID_FLAG_1, null));
			}
		});
		IMethodRegisterService service = null;
		// 判断本地是否有IMethodSerivce实现
		try {
			service = provider.getBean(IMethodRegisterService.class);
		} catch (Exception ex) {

		}
		// 有就用
		if (null != service) {
			service.register(vos);
			log(vos);
		}
		// 没有就调服务
		else {
			JsonContainer<List<Method>> result = client.register(vos);
			checkJsonContainer(result);
			log(result.getData());
		}
	}

	/**
	 * 记录日志
	 *
	 * @param result
	 */
	private void log(List<Method> result) {
		for (Method vo : result) {
			log.info(JSON.toJSONString(vo));
		}
	}

}
