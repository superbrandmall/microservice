package com.sbm.module.common.authorization.api.businesscode.biz;


import com.sbm.module.common.authorization.api.businesscode.domain.BusinessCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBusinessCodeService {

	Page<BusinessCode> findAll(Pageable pageable);

	void refresh();

	BusinessCode findOneByBusinessClazzAndBusinessCode(String businessClazz, String businessCode);

}