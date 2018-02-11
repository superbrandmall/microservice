package com.sbm.module.onlineleasing.base.brand.biz;

import com.sbm.module.onlineleasing.base.brand.domain.TOLBrand;
import com.sbm.module.onlineleasing.data.biz.IOLDataService;

public interface ITOLBrandService extends IOLDataService<TOLBrand, Integer> {

	TOLBrand findOneByHdUuid(String hdUuid);

}
