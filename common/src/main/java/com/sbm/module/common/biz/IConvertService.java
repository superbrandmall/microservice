package com.sbm.module.common.biz;

import com.sbm.module.common.domain.BaseEntity;

public interface IConvertService<M extends BaseEntity, N> {

	/**
	 * 转换
	 *
	 * @param po
	 * @param vo
	 * @return
	 */
	M convert(M po, N vo);

}
