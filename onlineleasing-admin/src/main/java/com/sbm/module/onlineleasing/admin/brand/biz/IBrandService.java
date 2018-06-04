package com.sbm.module.onlineleasing.admin.brand.biz;

import com.sbm.module.onlineleasing.domain.brand.Brand;

public interface IBrandService {

	/**
	 * 通过品牌编号查询
	 *
	 * @param code
	 * @return
	 */
	Brand findOneByCode(String code);

}
