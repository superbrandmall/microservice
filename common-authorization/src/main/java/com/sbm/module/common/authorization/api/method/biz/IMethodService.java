package com.sbm.module.common.authorization.api.method.biz;


import com.sbm.module.common.authorization.api.method.domain.Method;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMethodService {

	Page<Method> findAll(Pageable pageable);

}
