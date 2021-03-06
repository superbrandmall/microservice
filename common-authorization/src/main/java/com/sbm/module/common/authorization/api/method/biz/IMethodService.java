package com.sbm.module.common.authorization.api.method.biz;


import com.sbm.module.common.authorization.api.method.domain.Method;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IMethodService {

	Page<Method> findAll(Pageable pageable);

	void refresh();

	Method findOneByPathAndMethod(String path, String method);

	List<Method> findAllByApplicationName(String applicationName);
}
