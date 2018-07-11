package com.sbm.module.onlineleasing.admin.mall.biz;

import com.sbm.module.onlineleasing.domain.mall.Mall;
import com.sbm.module.onlineleasing.domain.mall.MallMaxInfo;
import com.sbm.module.onlineleasing.domain.mall.MallQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IMallService {

	Page<Mall> findAll(MallQuery query, Pageable pageable);

	MallMaxInfo findOneByMallCode(String mallCode);

	void save(MallMaxInfo mallMaxInfo);

}
