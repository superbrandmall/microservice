package com.sbm.module.onlineleasing.base.brand.biz;

import com.sbm.module.onlineleasing.base.brand.domain.TOLBrand;
import com.sbm.module.onlineleasing.data.biz.IOLDataService;

import java.util.List;

public interface ITOLBrandService extends IOLDataService<TOLBrand, Integer> {

	TOLBrand newInstance();

	TOLBrand findOneByHdUuid(String hdUuid);

	List<TOLBrand> findAllByNameContaining(String name);


}
