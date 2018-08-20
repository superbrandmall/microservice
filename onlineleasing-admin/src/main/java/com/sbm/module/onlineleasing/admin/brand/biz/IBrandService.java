package com.sbm.module.onlineleasing.admin.brand.biz;

import com.sbm.module.onlineleasing.domain.brand.Brand;
import com.sbm.module.onlineleasing.domain.brand.BrandMinInfo;
import com.sbm.module.onlineleasing.domain.brand.BrandQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBrandService {

	Page<BrandMinInfo> findAll(BrandQuery query, Pageable pageable);

	Brand findOneByCode(String code);

}
