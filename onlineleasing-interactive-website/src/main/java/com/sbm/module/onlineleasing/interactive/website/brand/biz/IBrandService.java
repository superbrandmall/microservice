package com.sbm.module.onlineleasing.interactive.website.brand.biz;

import com.sbm.module.onlineleasing.interactive.website.brand.domain.Brand;
import com.sbm.module.onlineleasing.interactive.website.brand.domain.BrandQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBrandService {

	Page<Brand> findAll(BrandQuery query, Pageable pageable);

}
